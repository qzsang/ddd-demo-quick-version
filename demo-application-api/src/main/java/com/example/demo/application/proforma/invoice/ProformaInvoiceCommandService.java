package com.example.demo.application.proforma.invoice;

import com.example.demo.application.proforma.invoice.dto.InvoiceSaveRequest;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/29
 */
public interface ProformaInvoiceCommandService {

    /**
     * 发票保存
     * @param invoiceSaveRequest
     * @param uid 操作人
     * @param username 操作人名字
     */
    void save(InvoiceSaveRequest invoiceSaveRequest, Long uid, String username);


    /**
     * 发票直接提交
     * @param invoiceId
     * @param uid 操作人
     * @param username 操作人名字
     */
    void submit(Long invoiceId, Long uid, String username);
}
