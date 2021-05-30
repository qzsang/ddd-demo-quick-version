package com.example.demo.infrastructure.database.proforma.invoice.converter;

import com.example.demo.domain.proforma.invoice.bo.GoodsBO;
import com.example.demo.domain.proforma.invoice.bo.ProformaInvoiceBO;
import com.example.demo.infrastructure.database.proforma.invoice.po.ProformaInvoiceGoodsPO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/29
 */
public class ProformaInvoiceGoodsConverter {

    public static GoodsBO toBO(ProformaInvoiceGoodsPO goodsPO) {
        GoodsBO goodsBO = new GoodsBO();
        BeanUtils.copyProperties(goodsPO, goodsBO);
        return goodsBO;
    }

    public static ProformaInvoiceGoodsPO toPO(GoodsBO goodsBO) {
        ProformaInvoiceGoodsPO goodsPO = new ProformaInvoiceGoodsPO();
        BeanUtils.copyProperties(goodsBO, goodsPO);
        return goodsPO;
    }

    public static List<ProformaInvoiceGoodsPO> toPOList(ProformaInvoiceBO invoiceBO) {
        if (invoiceBO == null || CollectionUtils.isEmpty(invoiceBO.getGoodsList())) {
            return null;
        }
        List<ProformaInvoiceGoodsPO> list = new ArrayList<>(invoiceBO.getGoodsList().size());
        invoiceBO.getGoodsList().forEach(goodsBO -> list.add(ProformaInvoiceGoodsConverter.toPO(goodsBO)));
        return list;
    }
}
