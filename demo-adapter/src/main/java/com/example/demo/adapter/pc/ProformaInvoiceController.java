package com.example.demo.adapter.pc;

import com.example.demo.application.proforma.invoice.ProformaInvoiceCommandService;
import com.example.demo.application.proforma.invoice.ProformaInvoiceQueryService;
import com.example.demo.application.proforma.invoice.dto.InvoiceInfoDTO;
import com.example.demo.application.proforma.invoice.dto.InvoiceSaveRequest;
import com.example.demo.application.proforma.invoice.dto.InvoiceSearchItemDTO;
import com.example.demo.application.proforma.invoice.dto.InvoiceSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author qzs
 * @version 1.0
 * @date 2021/5/19 19:52
 */
@RestController
@RequestMapping("/finance/proforma/invoice")
public class ProformaInvoiceController {
    @Autowired
    private ProformaInvoiceCommandService proformaInvoiceCommandService;
    @Autowired
    private ProformaInvoiceQueryService proformaInvoiceQueryService;
    /**
     * 保存
     * @param invoiceSaveRequest
     */
    @PostMapping("/save")
    public void save(@RequestBody InvoiceSaveRequest invoiceSaveRequest) {
        proformaInvoiceCommandService.save(invoiceSaveRequest, 1L, "我是名字");
    }

    /**
     * 提交
     * @param invoiceId
     * @return
     * @throws IOException
     */
    @PutMapping("/submit")
    public void submit(Long invoiceId) {
        proformaInvoiceCommandService.submit(invoiceId, 1L, "我是名字");
    }

    /**
     * 发票详情
     * @param invoiceId
     * @return
     */
    @GetMapping("/invoice")
    public InvoiceInfoDTO queryById(Long invoiceId) {
        return proformaInvoiceQueryService.queryById(invoiceId);
    }

    /**
     * 发票列表
     * @param searchRequest
     * @return
     */
    @GetMapping("/search-list")
    public List<InvoiceSearchItemDTO> searchList(InvoiceSearchRequest searchRequest) {
        return proformaInvoiceQueryService.searchList(searchRequest);
    }

}
