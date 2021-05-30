package com.example.demo.domain.proforma.invoice.bo;


import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class GoodsBO implements Serializable {
    private static final long serialVersionUID = 1L;
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

    /**
     * 调整并填充
     */
    public void adjust() {
        if (priceTotal != null) {
            priceTotal = priceTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        if (quantity != null) {
            quantity = quantity.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        if (priceTotal != null && quantity != null && quantity.doubleValue() != 0) {
            unitPrice = priceTotal.divide(quantity, 2, BigDecimal.ROUND_HALF_UP);
        }
    }

    public static void validate(List<GoodsBO> goodsBOList) {
        if (CollectionUtils.isEmpty(goodsBOList)) {
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "商品明细");
            throw new RuntimeException("参数验证不通过");
        }
        goodsBOList.forEach(GoodsBO::validate);
    }

    public static void validate(GoodsBO goodsBO) {
        if (goodsBO == null || StringUtils.isEmpty(goodsBO.goodsName)) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "商品名字");
        }
        if (goodsBO.getQuantity() == null) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "商品数量");
        } else if (goodsBO.getQuantity().doubleValue() <= 0) {
//            throw new RRException(FinanceExceptionCode.PARAMETER_NOT_MATCH, "商品数量");
        }
        if (goodsBO.getPriceTotal() == null) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "商品价格");
        } else if (goodsBO.getPriceTotal().doubleValue() <= 0) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NOT_MATCH, "商品价格");
        }
    }
}
