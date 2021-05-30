package com.example.demo.domain.proforma.invoice.repository;

import com.example.demo.domain.proforma.invoice.bo.ProformaInvoiceBO;
import com.github.meixuesong.aggregatepersistence.Aggregate;

/**
 * 形式发票持久化服务
 * @author quezhongsang
 */
public interface ProformaInvoiceRepository {

    /**
     * 保存聚合
     * @param invoiceAggregate
     */
    void save(Aggregate<ProformaInvoiceBO> invoiceAggregate);

    /**
     * 通过id获取业务对象
     * @param id
     * @return
     */
    Aggregate<ProformaInvoiceBO> getProformaInvoice(Long id);
}
