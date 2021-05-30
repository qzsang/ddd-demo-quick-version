package com.example.demo.infrastructure.database.proforma.invoice.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.io.Serializable;

/**
 * 形式发票商品
 *
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021-05-18 17:19:51
 */
@TableName("t_proforma_invoice_goods")
@Data
public class ProformaInvoiceGoodsPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 发票id
     */
    private Long invoiceId;
    /**
     * 商品名字
     */
    private String goodsName;
    /**
     * 品种
     */
    private String category;
    /**
     * 包装
     */
    private String wrapper;
    /**
     * 规格
     */
    private String spec;
    /**
     * 数量
     */
    private BigDecimal quantity;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 合计
     */
    private BigDecimal priceTotal;

}
