package com.example.demo.infrastructure.database.proforma.invoice.converter;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.example.demo.domain.proforma.invoice.bo.ProformaInvoiceBO;
import com.example.demo.domain.proforma.invoice.bo.valueobject.CurrencyEnum;
import com.example.demo.domain.proforma.invoice.bo.valueobject.ProformaInvoiceStatusEnum;
import com.example.demo.domain.proforma.invoice.bo.valueobject.TradeConditionEnum;
import com.example.demo.infrastructure.database.proforma.invoice.po.ProformaInvoicePO;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/29
 */
public class ProformaInvoiceConverter {

    public static ProformaInvoicePO toPO(ProformaInvoiceBO proformaInvoiceBO) {
        ProformaInvoicePO proformaInvoicePO = new ProformaInvoicePO();
        proformaInvoicePO.setId(proformaInvoiceBO.getId());
        proformaInvoicePO.setInvoiceNo(proformaInvoiceBO.getInvoiceNo());
        proformaInvoicePO.setInvoiceDt(proformaInvoiceBO.getInvoiceDt());
        proformaInvoicePO.setInvoiceOprDt(proformaInvoiceBO.getInvoiceOprDt());
        proformaInvoicePO.setInvoicePriceTotal(proformaInvoiceBO.getPriceTotal());
        proformaInvoicePO.setInvoiceResultUrl(proformaInvoiceBO.getResultUrl());
        proformaInvoicePO.setVersion(proformaInvoiceBO.getVersion());
        proformaInvoicePO.setIsDelete(proformaInvoiceBO.getIsDelete());
        if (proformaInvoiceBO.getStatus() != null) {
            proformaInvoicePO.setInvoiceStatus(proformaInvoiceBO.getStatus().getCode());
        }
        if (proformaInvoiceBO.getApplyUser() != null) {
            proformaInvoicePO.setApplyUid(proformaInvoiceBO.getApplyUser().getUid());
            proformaInvoicePO.setApplyName(proformaInvoiceBO.getApplyUser().getUserName());
        }
        if (proformaInvoiceBO.getOprUser() != null) {
            proformaInvoicePO.setOprUid(proformaInvoiceBO.getOprUser().getUid());
            proformaInvoicePO.setOprName(proformaInvoiceBO.getOprUser().getUserName());
            proformaInvoicePO.setOprReason(proformaInvoiceBO.getOprUser().getReason());
        }
        if (proformaInvoiceBO.getCurrency() != null) {
            proformaInvoicePO.setCurrency(proformaInvoiceBO.getCurrency().getMean());
        }
        if (proformaInvoiceBO.getTradeCondition() != null) {
            proformaInvoicePO.setTradeCondition(proformaInvoiceBO.getTradeCondition().getMean());
        }
        if (proformaInvoiceBO.getConsumer() != null) {
            proformaInvoicePO.setConsumerName(proformaInvoiceBO.getConsumer().getName());
            proformaInvoicePO.setConsumerMobile(proformaInvoiceBO.getConsumer().getMobile());
            proformaInvoicePO.setConsumerFax(proformaInvoiceBO.getConsumer().getFax());
            proformaInvoicePO.setConsumerAddr(proformaInvoiceBO.getConsumer().getAddr());
        }
        if (proformaInvoiceBO.getSupplier() != null) {
            proformaInvoicePO.setSupplier(proformaInvoiceBO.getSupplier().getSupplierName());
        }
        if (CollectionUtils.isNotEmpty(proformaInvoiceBO.getAccessoryList())) {
            proformaInvoicePO.setAccessoryList(AccessoryConverter.toListString(proformaInvoiceBO.getAccessoryList()));
        }
        if (proformaInvoiceBO.getShowedContainerNo() != null) {
            proformaInvoicePO.setShowedContainerNo(proformaInvoiceBO.getShowedContainerNo() ? 1 : 0);
        }
        if (CollectionUtils.isNotEmpty(proformaInvoiceBO.getContainerNoList())) {
            proformaInvoicePO.setContainerNoList(ContainerNoConverter.toListString(proformaInvoiceBO.getContainerNoList()));
        }
        return proformaInvoicePO;
    }

    public static ProformaInvoiceBO toBO(ProformaInvoicePO invoicePO) {
        if (invoicePO == null) {
            return null;
        }
        ProformaInvoiceBO proformaInvoiceBO = new ProformaInvoiceBO();
        proformaInvoiceBO.setId(invoicePO.getId());
        proformaInvoiceBO.setInvoiceNo(invoicePO.getInvoiceNo());
        proformaInvoiceBO.setInvoiceDt(invoicePO.getInvoiceDt());
        proformaInvoiceBO.setInvoiceOprDt(invoicePO.getInvoiceOprDt());
        proformaInvoiceBO.setPriceTotal(invoicePO.getInvoicePriceTotal());
        proformaInvoiceBO.setResultUrl(invoicePO.getInvoiceResultUrl());
        proformaInvoiceBO.setStatus(ProformaInvoiceStatusEnum.toEnum(invoicePO.getInvoiceStatus()));
        proformaInvoiceBO.setApplyUser(ApplyUserConverter.toBO(invoicePO.getApplyUid(), invoicePO.getApplyName()));
        proformaInvoiceBO.setOprUser(OprUserConverter.toBO(invoicePO.getOprUid(), invoicePO.getOprName(), invoicePO.getOprReason()));
        proformaInvoiceBO.setCurrency(CurrencyEnum.toEnum(invoicePO.getCurrency()));
        proformaInvoiceBO.setTradeCondition(TradeConditionEnum.toEnum(invoicePO.getTradeCondition()));
        proformaInvoiceBO.setConsumer(ConsumerConverter.toBO(invoicePO));
        proformaInvoiceBO.setSupplier(SupplierConverter.toBO(invoicePO.getSupplier()));
        proformaInvoiceBO.setAccessoryList(AccessoryConverter.toListBO(invoicePO.getAccessoryList()));
        proformaInvoiceBO.setShowedContainerNo(new Integer(1).equals(invoicePO.getShowedContainerNo()));
        proformaInvoiceBO.setContainerNoList(ContainerNoConverter.toListBO(invoicePO.getContainerNoList()));
        proformaInvoiceBO.setVersion(invoicePO.getVersion());
        proformaInvoiceBO.setIsDelete(invoicePO.getIsDelete());
        return proformaInvoiceBO;
    }
}
