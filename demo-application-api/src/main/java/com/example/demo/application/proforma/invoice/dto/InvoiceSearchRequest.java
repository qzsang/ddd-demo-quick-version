package com.example.demo.application.proforma.invoice.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 发票搜索请求
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class InvoiceSearchRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    // @ApiModelProperty(value = "发票编号")
    private String invoiceNo;
}
