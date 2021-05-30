package com.example.demo.domain.proforma.invoice.service;

import com.example.demo.domain.proforma.invoice.bo.ProformaInvoiceBO;
import com.github.meixuesong.aggregatepersistence.Aggregate;

/**
 * 形式发票服务
 * @author quezhongsang
 */
public interface ProformaInvoiceDomainService {

    /**
     * 生成结果 目前是PDF
     * @param invoiceAggregate
     */
    void generateResult(Aggregate<ProformaInvoiceBO> invoiceAggregate);
}
