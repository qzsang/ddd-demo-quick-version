package com.example.demo.infrastructure.database.proforma.invoice.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.github.meixuesong.aggregatepersistence.Versionable;
import lombok.Data;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 形式发票
 *
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021-05-18 17:19:51
 */
@TableName("t_proforma_invoice")
@Data
public class ProformaInvoicePO implements Serializable, Versionable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 发票编号，作废或者删除的发票改为由原编号+序号
     */
    private String invoiceNo;
    /**
     * 发票时间
     */
    private Date invoiceDt;
    /**
     * 发票创建时间
     */
    private Date invoiceOprDt;
    /**
     * 发票合计金额
     */
    private BigDecimal invoicePriceTotal;
    /**
     * 发票状态，0：待提交  1：待审核  2：已开票  -1：已作废
     */
    private Integer invoiceStatus;
    /**
     * 结果url,目前是pdf文件地址
     */
    private String invoiceResultUrl;
    /**
     * 币种
     */
    private String currency;
    /**
     * 供应商
     */
    private String supplier;
    /**
     * 贸易条件
     */
    private String tradeCondition;
    /**
     * 购方-机构
     */
    private String consumerName;
    /**
     * 购方-地址
     */
    private String consumerAddr;
    /**
     * 购方-电话
     */
    private String consumerMobile;
    /**
     * 购方-传真
     */
    private String consumerFax;
    /**
     * 是否显示柜号 0：不显示；1：显示
     */
    @TableField("is_show_container_no")
    private Integer showedContainerNo;
    /**
     * 柜号列表，"/"为分割标识
     */
    private String containerNoList;
    /**
     * 附件列表，’,‘作为分隔符
     */
    private String accessoryList;

    /**
     * 申请人-id
     */
    private Long applyUid;
    /**
     * 申请人-名字
     */
    private String applyName;
    /**
     * 审核人-id
     */
    private Long oprUid;
    /**
     * 审核人-名字
     */
    private String oprName;
    /**
     * 审核人-理由
     */
    private String oprReason;
    /**
     * 版本号
     */
    private int version;
    /**
     * 是否删除
     */
    private Integer isDelete;

    @Override
    public int getVersion() {
        return version;
    }
}
