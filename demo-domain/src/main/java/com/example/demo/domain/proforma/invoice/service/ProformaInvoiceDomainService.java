package com.example.demo.domain.proforma.invoice.service;

import com.example.demo.domain.proforma.invoice.bo.ProformaInvoiceBO;
import com.github.meixuesong.aggregatepersistence.Aggregate;

/**
 * 形式发票领域服务
 *
 * 不是任何实体和值对象的职责，或者一些个性化业务需求，无法为其建模时。"与其勉强归类，不如新建一个领域服务来处理"
 * @author quezhongsang
 */
public interface ProformaInvoiceDomainService {

    /**
     * 将聚合的结果保存独立成接口，避免直接通过set设置，影响后续迭代
     * @param invoiceAggregate
     */
    void saveResult(Aggregate<ProformaInvoiceBO> invoiceAggregate, String resultUrl);
}
