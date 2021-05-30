package com.example.demo.application.proforma.invoice;

import com.example.demo.application.proforma.invoice.dto.InvoiceInfoDTO;
import com.example.demo.application.proforma.invoice.dto.InvoiceSearchItemDTO;
import com.example.demo.application.proforma.invoice.dto.InvoiceSearchRequest;

import java.util.List;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/29
 */
public interface ProformaInvoiceQueryService {

    /**
     * 形式发票详情
     * @param invoiceId
     * @return
     */
    InvoiceInfoDTO queryById(Long invoiceId);

    /**
     * 搜索列表
     * @param searchRequest
     * @return
     */
    List<InvoiceSearchItemDTO> searchList(InvoiceSearchRequest searchRequest);
}
