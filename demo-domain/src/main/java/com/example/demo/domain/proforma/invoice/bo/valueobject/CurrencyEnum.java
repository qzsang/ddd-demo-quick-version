package com.example.demo.domain.proforma.invoice.bo.valueobject;

/**
 * 形式发票币种
 * @author qzsang
 * @version 1.0
 * @date 2021/5/18 13:57
 */
public enum CurrencyEnum {
    /**
     * RMB
     */
    RMB("RMB"),
    /**
     * USD
     */
    USD("USD"),
    /**
     * AUD
     */
    AUD("AUD"),
    ;

    public String getMean() {
        return mean;
    }

    CurrencyEnum(String mean) {
        this.mean = mean;
    }

    private String mean;

    public static CurrencyEnum toEnum(String mean) {
        if (mean == null) {
            return null;
        }
        for (CurrencyEnum en : values()) {
            if (en.getMean().equals(mean)) {
                return en;
            }
        }
        return null;
    }
}
