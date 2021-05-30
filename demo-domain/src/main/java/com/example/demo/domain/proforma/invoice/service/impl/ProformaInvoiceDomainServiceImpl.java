package com.example.demo.domain.proforma.invoice.service.impl;

import com.example.demo.domain.proforma.invoice.bo.ProformaInvoiceBO;
import com.example.demo.domain.proforma.invoice.bo.valueobject.ProformaInvoiceStatusEnum;
import com.example.demo.domain.proforma.invoice.repository.ProformaInvoiceRepository;
import com.example.demo.domain.proforma.invoice.service.ProformaInvoiceDomainService;
import com.github.meixuesong.aggregatepersistence.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 形式发票服务
 * @author quezhongsang
 */
@Service
public class ProformaInvoiceDomainServiceImpl implements ProformaInvoiceDomainService {
    @Autowired
    private ProformaInvoiceRepository proformaInvoiceRepository;

    @Override
    public void saveResult(Aggregate<ProformaInvoiceBO> invoiceAggregate, String resultUrl) {
        // 双重校验
        if (!ProformaInvoiceStatusEnum.NORMAL.equals(invoiceAggregate.getRoot().getStatus())) {
            return;
        } else {
            invoiceAggregate.getRoot().setResultUrl(resultUrl);
            proformaInvoiceRepository.save(invoiceAggregate);
        }
    }
}
