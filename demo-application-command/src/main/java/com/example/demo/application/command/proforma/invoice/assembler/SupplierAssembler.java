package com.example.demo.application.command.proforma.invoice.assembler;

import com.example.demo.application.proforma.invoice.dto.InvoiceSaveRequest;
import com.example.demo.domain.proforma.invoice.bo.valueobject.SupplierBO;
import com.example.demo.infrastructure.database.proforma.invoice.converter.SupplierConverter;
import lombok.Data;

/**
 * 装配器
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class SupplierAssembler {

    public static SupplierBO toBO(InvoiceSaveRequest saveRequest) {
        if (saveRequest != null) {
           return SupplierConverter.toBO(saveRequest.getSupplierName());
        } else {
            return new SupplierBO();
        }
    }

}
