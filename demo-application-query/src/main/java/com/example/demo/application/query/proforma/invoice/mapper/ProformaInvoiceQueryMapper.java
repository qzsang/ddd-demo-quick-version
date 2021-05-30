package com.example.demo.application.query.proforma.invoice.mapper;

import com.example.demo.application.proforma.invoice.dto.InvoiceSearchItemDTO;
import com.example.demo.application.proforma.invoice.dto.InvoiceSearchRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/29
 */
@Mapper
public interface ProformaInvoiceQueryMapper {

//    InvoiceInfoDTO queryById(@Param("invoiceId") Long invoiceId);

    List<InvoiceSearchItemDTO> searchList(@Param("searchRequest") InvoiceSearchRequest searchRequest);
}
