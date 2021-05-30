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

//    @Async(ThreadConfig.THREAD_NAME_PROFORMA_INVOICE_RESULT)
    @Override
    public void generateResult(Aggregate<ProformaInvoiceBO> invoiceAggregate) {
        if (!ProformaInvoiceStatusEnum.NORMAL.equals(invoiceAggregate.getRoot().getStatus())) {
            return;
        }
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String resultUrl = "https://www.baidu.com";
        // 双重校验
        if (!ProformaInvoiceStatusEnum.NORMAL.equals(invoiceAggregate.getRoot().getStatus())) {
            return;
        } else {
            invoiceAggregate.getRoot().setResultUrl(resultUrl);
            // 这边更新的时候 如果有并发 会导致这里更新失败，但是正常业务已开票也不能操作，除非作废和重新变成草稿状态
            proformaInvoiceRepository.save(invoiceAggregate);
        }
    }
}
