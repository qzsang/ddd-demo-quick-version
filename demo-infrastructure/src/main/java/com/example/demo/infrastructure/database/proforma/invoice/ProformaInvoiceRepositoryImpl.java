package com.example.demo.infrastructure.database.proforma.invoice;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.example.demo.domain.proforma.invoice.bo.GoodsBO;
import com.example.demo.domain.proforma.invoice.bo.ProformaInvoiceBO;
import com.example.demo.domain.proforma.invoice.repository.ProformaInvoiceRepository;
import com.example.demo.infrastructure.database.proforma.invoice.converter.ProformaInvoiceConverter;
import com.example.demo.infrastructure.database.proforma.invoice.converter.ProformaInvoiceGoodsConverter;
import com.example.demo.infrastructure.database.proforma.invoice.po.ProformaInvoiceGoodsPO;
import com.example.demo.infrastructure.database.proforma.invoice.po.ProformaInvoicePO;
import com.github.meixuesong.aggregatepersistence.Aggregate;
import com.github.meixuesong.aggregatepersistence.AggregateFactory;
import com.github.meixuesong.aggregatepersistence.DataObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 形式发票持久化服务
 * @author quezhongsang
 */
@Repository
public class ProformaInvoiceRepositoryImpl implements ProformaInvoiceRepository {

    @Autowired(required = true)
    private ProformaInvoiceMapper proformaInvoiceMapper;
    @Autowired
    private ProformaInvoiceGoodsMapper proformaInvoiceGoodsMapper;

    @Override
    public void save(Aggregate<ProformaInvoiceBO> invoiceAggregate) {
        invoiceAggregate.getRoot().validate();
        if (invoiceAggregate.isNew()) {
            this.insertNewAggregate(invoiceAggregate);
        } else if (invoiceAggregate.isChanged()) {
            this.updateAggregateRoot(invoiceAggregate);
        }
        // 内存同步版本号
        invoiceAggregate.getRoot().setVersion(invoiceAggregate.getRoot().getVersion() + 1);
    }

    @Override
    public Aggregate<ProformaInvoiceBO> getProformaInvoice(Long id) {
        if (id == null) {
            return AggregateFactory.createAggregate(new ProformaInvoiceBO());
        }
        ProformaInvoicePO invoicePO = proformaInvoiceMapper.selectById(id);
        if (invoicePO == null) {
            return AggregateFactory.createAggregate(new ProformaInvoiceBO());
        }
        List<ProformaInvoiceGoodsPO> goodPOList = proformaInvoiceGoodsMapper.selectList(new EntityWrapper<ProformaInvoiceGoodsPO>()
                .eq("invoice_id", id));

        ProformaInvoiceBO proformaInvoiceBO = ProformaInvoiceConverter.toBO(invoicePO);

        if (CollectionUtils.isNotEmpty(goodPOList)) {
            List<GoodsBO> goodsBOList = new ArrayList<>(goodPOList.size());
            goodPOList.forEach(goodsPO -> goodsBOList.add(ProformaInvoiceGoodsConverter.toBO(goodsPO)));
            proformaInvoiceBO.setGoodsList(goodsBOList);
        }
        Aggregate<ProformaInvoiceBO> invoiceAggregate = AggregateFactory.createAggregate(proformaInvoiceBO);
        return invoiceAggregate;
    }

    /**
     * 插入聚合
     * @param invoiceAggregate
     */
    private void insertNewAggregate(Aggregate<ProformaInvoiceBO> invoiceAggregate) {
        ProformaInvoicePO proformaInvoicePO = ProformaInvoiceConverter.toPO(invoiceAggregate.getRoot());
        List<ProformaInvoiceGoodsPO> goodPOList = ProformaInvoiceGoodsConverter.toPOList(invoiceAggregate.getRoot());

        proformaInvoicePO.setVersion(1);
        proformaInvoiceMapper.insert(proformaInvoicePO);
        if (CollectionUtils.isNotEmpty(goodPOList)) {
            goodPOList.forEach(proformaInvoiceGoodsEntity -> {
                proformaInvoiceGoodsEntity.setInvoiceId(proformaInvoicePO.getId());
                proformaInvoiceGoodsMapper.insert(proformaInvoiceGoodsEntity);
            });
        }
        invoiceAggregate.getRoot().setId(proformaInvoicePO.getId());
    }

    /**
     * 更新聚合
     * @param invoiceAggregate
     */
    private void updateAggregateRoot(Aggregate<ProformaInvoiceBO> invoiceAggregate) {
        Long invoiceId = invoiceAggregate.getRoot().getId();

        ProformaInvoiceBO updProformaInvoiceBO = DataObjectUtils.getDelta(invoiceAggregate.getRootSnapshot(), invoiceAggregate.getRoot());

        ProformaInvoicePO proformaInvoicePO = ProformaInvoiceConverter.toPO(invoiceAggregate.getRoot());
        Set<String> changedFields = DataObjectUtils.getChangedFields(ProformaInvoiceConverter.toPO(invoiceAggregate.getRootSnapshot()), proformaInvoicePO);

        List<ProformaInvoiceGoodsPO> goodPOList = ProformaInvoiceGoodsConverter.toPOList(updProformaInvoiceBO);

        Integer updateResult =
                proformaInvoiceMapper.updateByIdSelective(proformaInvoicePO, changedFields);
        if (updateResult == null || updateResult <= 0) {
            throw new RuntimeException("存在并发请重试");
        }
        if (CollectionUtils.isNotEmpty(goodPOList)) {
            proformaInvoiceGoodsMapper.delete(
                    new EntityWrapper<ProformaInvoiceGoodsPO>()
                            .eq("invoice_id", invoiceId));
            goodPOList.forEach(proformaInvoiceGoodsEntity -> {
                proformaInvoiceGoodsEntity.setInvoiceId(invoiceId);
                proformaInvoiceGoodsMapper.insert(proformaInvoiceGoodsEntity);
            });
        }
        // 插入日志
        proformaInvoicePO.setId(invoiceId);
    }

}
