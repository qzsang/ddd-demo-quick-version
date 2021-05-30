package com.example.demo.application.command.proforma.invoice.assembler;

import com.example.demo.application.proforma.invoice.dto.InvoiceSaveRequest;
import com.example.demo.domain.proforma.invoice.bo.valueobject.ContainerNoBO;
import com.example.demo.infrastructure.database.proforma.invoice.converter.ContainerNoConverter;

import java.util.List;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/20
 */
public class ContainerNoAssembler {

    public static List<ContainerNoBO> toListBO(InvoiceSaveRequest saveRequest) {
        if (saveRequest != null) {
            return ContainerNoConverter.toListBO(saveRequest.getContainerNoList());
        } else {
            return ContainerNoConverter.toListBO("");
        }
    }

}
