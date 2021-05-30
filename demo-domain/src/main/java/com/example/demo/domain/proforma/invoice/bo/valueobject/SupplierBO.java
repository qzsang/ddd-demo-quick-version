package com.example.demo.domain.proforma.invoice.bo.valueobject;


import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 供应商值对象
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class SupplierBO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 名字
     */
    private String supplierName;

    public static void validate(SupplierBO supplierBO) {
        if (supplierBO == null || StringUtils.isEmpty(supplierBO.getSupplierName())) {
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "供应商");
            throw new RuntimeException("参数验证不通过");
        }
    }
}
