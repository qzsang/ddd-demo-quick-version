package com.example.demo.application.proforma.invoice.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 发票搜索子项
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class InvoiceSearchItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // @ApiModelProperty(value = "主键")
    private Long id;
    // @ApiModelProperty(value = "发票编号，作废或者删除的发票改为由原编号+序号")
    private String invoiceNo;
    // @ApiModelProperty(value = "购方-机构")
    private String consumerName;
}
