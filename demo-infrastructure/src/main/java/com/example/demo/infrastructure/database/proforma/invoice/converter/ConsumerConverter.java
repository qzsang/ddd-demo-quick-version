package com.example.demo.infrastructure.database.proforma.invoice.converter;

import com.example.demo.domain.proforma.invoice.bo.valueobject.ConsumerBO;
import com.example.demo.infrastructure.database.proforma.invoice.po.ProformaInvoicePO;
import lombok.Data;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class ConsumerConverter {

    public static ConsumerBO toBO(ProformaInvoicePO invoicePO) {
        if (invoicePO == null) {
            return null;
        }
        ConsumerBO consumerBO = new ConsumerBO();
        consumerBO.setName(invoicePO.getConsumerName());
        consumerBO.setAddr(invoicePO.getConsumerAddr());
        consumerBO.setFax(invoicePO.getConsumerFax());
        consumerBO.setMobile(invoicePO.getConsumerMobile());
        return consumerBO;
    }
}
