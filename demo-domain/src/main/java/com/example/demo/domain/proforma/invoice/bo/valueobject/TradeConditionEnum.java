package com.example.demo.domain.proforma.invoice.bo.valueobject;

/**
 * 形式发票贸易条件
 * @author qzsang
 * @version 1.0
 * @date 2021/5/18 13:57
 */
public enum TradeConditionEnum {
    /**
     * FOB
     */
    FOB("FOB"),
    /**
     * CIF
     */
    CIF("CIF"),
    /**
     * CFR
     */
    CFR("CFR"),
    /**
     * CNF
     */
    CNF("CNF"),
    ;

    public String getMean() {
        return mean;
    }

    TradeConditionEnum(String mean) {
        this.mean = mean;
    }

    private String mean;

    public static TradeConditionEnum toEnum(String mean) {
        if (mean == null) {
            return null;
        }
        for (TradeConditionEnum en : values()) {
            if (en.getMean().equals(mean)) {
                return en;
            }
        }
        return null;
    }
}
