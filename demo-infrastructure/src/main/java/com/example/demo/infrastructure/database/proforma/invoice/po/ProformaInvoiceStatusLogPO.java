package com.example.demo.infrastructure.database.proforma.invoice.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 形式发票状态日志
 *
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021-05-18 17:19:51
 */
@TableName("t_proforma_invoice_status_log")
@Data
public class ProformaInvoiceStatusLogPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 发票id
     */
    private Long invoiceId;
    /**
     * 操作状态
     */
    private Integer invoiceStatus;
    /**
     * 操作-id
     */
    private Long oprUid;
    /**
     * 操作-名字
     */
    private String oprName;
    /**
     * 操作-理由
     */
    private String oprReason;
    /**
     * 时间
     */
    private Date oprDt;

}
