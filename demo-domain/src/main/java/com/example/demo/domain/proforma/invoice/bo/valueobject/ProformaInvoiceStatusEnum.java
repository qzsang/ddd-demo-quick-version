package com.example.demo.domain.proforma.invoice.bo.valueobject;

/**
 * 形式发票状态
 * @author qzsang
 * @version 1.0
 * @date 2021/5/18 13:57
 */
public enum ProformaInvoiceStatusEnum {
    /**
     * 待提交
     */
    DRAFT(0,"待提交"),
    /**
     * 待审核
     */
    EXAMINING(1,"待审核"),
    /**
     * 已开票
     */
    NORMAL(2,"已开票"),
    /**
     * 已作废
     */
    CANCEL(-1,"已作废")

    ;

    public Integer getCode() {
        return code;
    }


    public String getMean() {
        return mean;
    }


    ProformaInvoiceStatusEnum(Integer code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    private Integer code;
    private String mean;

    public static ProformaInvoiceStatusEnum toEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (ProformaInvoiceStatusEnum en : values()) {
            if (en.getCode().equals(code)) {
                return en;
            }
        }
        return null;
    }
}
