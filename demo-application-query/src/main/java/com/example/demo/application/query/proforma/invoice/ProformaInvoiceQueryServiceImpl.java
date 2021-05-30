package com.example.demo.application.query.proforma.invoice;

import com.example.demo.application.proforma.invoice.ProformaInvoiceQueryService;
import com.example.demo.application.proforma.invoice.dto.InvoiceInfoDTO;
import com.example.demo.application.proforma.invoice.dto.InvoiceSearchItemDTO;
import com.example.demo.application.proforma.invoice.dto.InvoiceSearchRequest;
import com.example.demo.application.query.proforma.invoice.mapper.ProformaInvoiceQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/29
 */
@Service
public class ProformaInvoiceQueryServiceImpl implements ProformaInvoiceQueryService {

    @Autowired
    private ProformaInvoiceQueryMapper proformaInvoiceQueryMapper;
    /**
     * 形式发票详情
     * @param invoiceId
     * @return
     */
    @Override
    public InvoiceInfoDTO queryById(Long invoiceId) {

//        proformaInvoiceQueryMapper.queryById(invoiceId);

        return null;
    }

    @Override
    public List<InvoiceSearchItemDTO> searchList(InvoiceSearchRequest searchRequest) {
        return proformaInvoiceQueryMapper.searchList(searchRequest);
    }
}
