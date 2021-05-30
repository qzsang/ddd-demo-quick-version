package com.example.demo.application.command.proforma.invoice.assembler;


import com.example.demo.application.proforma.invoice.dto.InvoiceSaveRequest;
import com.example.demo.domain.proforma.invoice.bo.GoodsBO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/20
 */
public class GoodsAssembler {

    public static List<GoodsBO> toListBO(InvoiceSaveRequest saveRequest) {
        if (saveRequest != null && !CollectionUtils.isEmpty(saveRequest.getGoodsList())) {
            List<GoodsBO> list = new ArrayList<>(saveRequest.getGoodsList().size());
            saveRequest.getGoodsList().forEach(goods -> {
                GoodsBO goodsBO = new GoodsBO();
                BeanUtils.copyProperties(goods, goodsBO);
                goodsBO.adjust();
                list.add(goodsBO);
            });
            return list;
        } else {
            return new ArrayList<>();
        }
    }

}
