package com.example.demo.application.command.proforma.invoice.assembler;

import com.example.demo.application.proforma.invoice.dto.InvoiceSaveRequest;
import com.example.demo.domain.proforma.invoice.bo.valueobject.ConsumerBO;
import lombok.Data;


/**
 * 装配器
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class ConsumerAssembler {

    public static ConsumerBO toBO(InvoiceSaveRequest saveRequest) {
        ConsumerBO consumerBO = new ConsumerBO();
        if (saveRequest != null) {
            consumerBO.setName(saveRequest.getConsumerName());
            consumerBO.setAddr(saveRequest.getConsumerAddr());
            consumerBO.setMobile(saveRequest.getConsumerMobile());
            consumerBO.setFax(saveRequest.getConsumerFax());
        }
        return consumerBO;
    }
}
