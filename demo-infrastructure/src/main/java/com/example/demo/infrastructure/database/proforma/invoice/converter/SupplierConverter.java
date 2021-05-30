package com.example.demo.infrastructure.database.proforma.invoice.converter;

import com.example.demo.domain.proforma.invoice.bo.valueobject.SupplierBO;
import lombok.Data;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class SupplierConverter {

    public static SupplierBO toBO(String supplierName) {
        SupplierBO supplierBO = new SupplierBO();
        supplierBO.setSupplierName(supplierName);
        return supplierBO;
    }
}
