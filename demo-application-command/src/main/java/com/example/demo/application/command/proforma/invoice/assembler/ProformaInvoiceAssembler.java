package com.example.demo.application.command.proforma.invoice.assembler;

import com.example.demo.application.proforma.invoice.dto.InvoiceSaveRequest;
import com.example.demo.domain.proforma.invoice.bo.ProformaInvoiceBO;
import com.example.demo.domain.proforma.invoice.bo.valueobject.CurrencyEnum;
import com.example.demo.domain.proforma.invoice.bo.valueobject.TradeConditionEnum;
import java.util.Date;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/20
 */
public class ProformaInvoiceAssembler {

    public static void infillToBO(ProformaInvoiceBO proformaInvoiceBO, InvoiceSaveRequest saveRequest) {
        if (proformaInvoiceBO == null) {
            throw new RuntimeException("参数错误");
//            throw FinanceExceptionCode.PROFORMA_INVOICE_NOT_EXIST;
        }
        if (saveRequest != null) {
            proformaInvoiceBO.setInvoiceNo(saveRequest.getInvoiceNo());
            if (saveRequest.getInvoiceTimestamp() != null) {
                proformaInvoiceBO.setInvoiceDt(new Date(saveRequest.getInvoiceTimestamp()));
            }
            proformaInvoiceBO.setShowedContainerNo(saveRequest.getShowedContainerNo());
            proformaInvoiceBO.setAccessoryList(saveRequest.getAccessoryList());
            proformaInvoiceBO.setGoodsList(GoodsAssembler.toListBO(saveRequest));
            proformaInvoiceBO.setCurrency(CurrencyEnum.toEnum(saveRequest.getCurrency()));
            proformaInvoiceBO.setSupplier(SupplierAssembler.toBO(saveRequest));
            proformaInvoiceBO.setTradeCondition(TradeConditionEnum.toEnum(saveRequest.getTradeCondition()));
            proformaInvoiceBO.setConsumer(ConsumerAssembler.toBO(saveRequest));
            proformaInvoiceBO.setContainerNoList(ContainerNoAssembler.toListBO(saveRequest));
        }
    }
}
