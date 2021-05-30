package com.example.demo.application.command.proforma.invoice;

import com.example.demo.application.command.proforma.invoice.assembler.ProformaInvoiceAssembler;
import com.example.demo.application.proforma.invoice.ProformaInvoiceCommandService;
import com.example.demo.application.proforma.invoice.dto.InvoiceSaveRequest;
import com.example.demo.domain.proforma.invoice.bo.ProformaInvoiceBO;
import com.example.demo.domain.proforma.invoice.bo.valueobject.ApplyUserBO;
import com.example.demo.domain.proforma.invoice.repository.ProformaInvoiceRepository;
import com.example.demo.infrastructure.database.proforma.invoice.converter.ApplyUserConverter;
import com.github.meixuesong.aggregatepersistence.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/29
 */
@Service
public class ProformaInvoiceCommandServiceImpl implements ProformaInvoiceCommandService {
    @Autowired
    private ProformaInvoiceRepository  proformaInvoiceRepository;

    @Override
    public void save(InvoiceSaveRequest invoiceSaveRequest, Long uid, String username) {
        // 从数据库获取业务模型 或者 新建业务模型 为填充准备
        Aggregate<ProformaInvoiceBO> invoiceBOAggregate = proformaInvoiceRepository.getProformaInvoice(invoiceSaveRequest.getId());
        // 填充业务模型
        ProformaInvoiceAssembler.infillToBO(invoiceBOAggregate.getRoot(), invoiceSaveRequest);
        // 业务逻辑操作
        invoiceBOAggregate.getRoot().save(ApplyUserConverter.toBO(uid, username));
        // 持久化
        proformaInvoiceRepository.save(invoiceBOAggregate);
    }

    @Override
    public void submit(Long invoiceId, Long uid, String username) {
        Aggregate<ProformaInvoiceBO> invoiceBOAggregate = proformaInvoiceRepository.getProformaInvoice(invoiceId);
        ApplyUserBO applyUserBO = ApplyUserConverter.toBO(uid, username);
        // 业务逻辑操作
        invoiceBOAggregate.getRoot().submit(applyUserBO);
        proformaInvoiceRepository.save(invoiceBOAggregate);
    }
}
