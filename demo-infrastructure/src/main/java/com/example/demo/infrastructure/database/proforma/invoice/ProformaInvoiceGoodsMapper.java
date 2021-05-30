package com.example.demo.infrastructure.database.proforma.invoice;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.infrastructure.database.proforma.invoice.po.ProformaInvoiceGoodsPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 形式发票商品
 * 
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021-05-18 17:19:51
 */
@Mapper
interface ProformaInvoiceGoodsMapper extends BaseMapper<ProformaInvoiceGoodsPO> {
	
}
