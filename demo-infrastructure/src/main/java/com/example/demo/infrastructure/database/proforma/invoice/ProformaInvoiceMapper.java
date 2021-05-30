package com.example.demo.infrastructure.database.proforma.invoice;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.infrastructure.database.proforma.invoice.po.ProformaInvoicePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Set;

/**
 * 形式发票 只对当前包暴露
 * 
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021-05-18 17:19:51
 */
@Mapper
interface ProformaInvoiceMapper extends BaseMapper<ProformaInvoicePO> {

    /**
     * 根据id选择性更新
     * @param invoicePO
     * @param changedFields
     * @return
     */
    int updateByIdSelective(@Param("invoice") ProformaInvoicePO invoicePO, @Param("changedFields") Set<String> changedFields);
}
