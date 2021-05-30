package com.example.demo.application.proforma.invoice.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 发票保存请求
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class InvoiceSaveRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    // @ApiModelProperty(value = "发票id")
    private Long id;
    // @ApiModelProperty(value = "发票编号", required = true)
    private String invoiceNo;
    // @ApiModelProperty(value = "贸易条件", required = true)
    private String tradeCondition;
    // @ApiModelProperty(value = "供应商", required = true)
    private String supplierName;
    // @ApiModelProperty(value = "发票时间搓", required = true)
    private Long invoiceTimestamp;
    // @ApiModelProperty(value = "购方名字", required = true)
    private String consumerName;
    // @ApiModelProperty(value = "购方地址", required = true)
    private String consumerAddr;
    // @ApiModelProperty(value = "购方电话")
    private String consumerMobile;
    // @ApiModelProperty(value = "购方传真")
    private String consumerFax;
    // @ApiModelProperty(value = "币种", required = true)
    private String currency;
    // @ApiModelProperty(value = "是否显示柜号", required = true)
    private Boolean showedContainerNo;
    // @ApiModelProperty(value = "柜号列表：必选：校验格式为4位英文+7位数字或者3位数字+“-”+7位数字，多柜号用/连接", required = true)
    private String containerNoList;
    // @ApiModelProperty(value = "商品列表，必传一个", required = true)
    private List<Goods> goodsList;
    // @ApiModelProperty(value = "附件列表", required = true)
    private List<String> accessoryList;

    @Data
//    @ApiModel("InvoiceSaveRequest_Goods")
    public static class Goods implements Serializable {
        private static final long serialVersionUID = 1L;
        // @ApiModelProperty(value = "商品名字", required = true)
        private String goodsName;
        // @ApiModelProperty(value = "品种")
        private String category;
        // @ApiModelProperty(value = "包装")
        private String wrapper;
        // @ApiModelProperty(value = "规格")
        private String spec;
        // @ApiModelProperty(value = "数量", required = true)
        private BigDecimal quantity;
        // @ApiModelProperty(value = "合计", required = true)
        private BigDecimal priceTotal;
    }

}
