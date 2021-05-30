package com.example.demo.application.proforma.invoice.dto;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 发票详情
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class InvoiceInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // @ApiModelProperty(value = "主键")
    private Long id;
    // @ApiModelProperty(value = "发票编号，作废或者删除的发票改为由原编号+序号")
    private String invoiceNo;
    // @ApiModelProperty(value = "发票创建时间搓")
    private Long invoiceOprTimestamp;
    // @ApiModelProperty(value = "发票时间")
    private Long invoiceTimestamp;
    // @ApiModelProperty(value = "发票合计金额")
    private BigDecimal invoicePriceTotal;
    // @ApiModelProperty(value = "发票状态，0：待提交  1：待审核  2：已开票  -1：已作废")
    private Integer invoiceStatus;
    // @ApiModelProperty(value = "结果url,目前是pdf文件地址")
    private String invoiceResultUrl;
    // @ApiModelProperty(value = "币种")
    private String currency;
    // @ApiModelProperty(value = "供应商")
    private String supplier;
    // @ApiModelProperty(value = "贸易条件")
    private String tradeCondition;
    // @ApiModelProperty(value = "购方-机构")
    private String consumerName;
    // @ApiModelProperty(value = "购方-地址")
    private String consumerAddr;
    // @ApiModelProperty(value = "购方-电话")
    private String consumerMobile;
    // @ApiModelProperty(value = "购方-传真")
    private String consumerFax;
    // @ApiModelProperty(value = "商品列表")
    private List<Goods> goodsList;
    // @ApiModelProperty(value = "是否显示柜号")
    private Boolean showedContainerNo;
    // @ApiModelProperty(value = "柜号列表，'/'为分割标识")
    private String containerNoList;
    // @ApiModelProperty(value = "审核人-理由")
    private String oprReason;
    // @ApiModelProperty(value = "附件列表，’,‘作为分隔符")
    private String accessoryList;

    @Data
//    @ApiModel("InvoiceInfoDTO_Goods")
    public static class Goods implements Serializable {
        private static final long serialVersionUID = 1L;
        // @ApiModelProperty(value = "商品名字")
        private String goodsName;
        // @ApiModelProperty(value = "品种")
        private String category;
        // @ApiModelProperty(value = "包装")
        private String wrapper;
        // @ApiModelProperty(value = "规格")
        private String spec;
        // @ApiModelProperty(value = "数量")
        private BigDecimal quantity;
        // @ApiModelProperty(value = "单价")
        private BigDecimal price;
        // @ApiModelProperty(value = "合计")
        private BigDecimal priceTotal;
    }

}
