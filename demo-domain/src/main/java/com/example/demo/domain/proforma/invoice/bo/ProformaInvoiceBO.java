package com.example.demo.domain.proforma.invoice.bo;

import com.example.demo.domain.proforma.invoice.bo.valueobject.*;
import com.github.meixuesong.aggregatepersistence.Versionable;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 形式发票业务模型
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class ProformaInvoiceBO implements Serializable, Versionable {
    private static final long serialVersionUID = 1L;
    /**
     * 发票id
     */
    private Long id;
    /**
     * 发票编号
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
    private BigDecimal priceTotal;
    /**
     * 结果url,目前是pdf文件地址
     */
    private String resultUrl;
    /**
     * 状态
     */
    private ProformaInvoiceStatusEnum status;
    /**
     * 申请人
     */
    private ApplyUserBO applyUser;
    /**
     * 审核人
     */
    private OprUserBO oprUser;
    /**
     * 币种
     */
    private CurrencyEnum currency;
    /**
     * 贸易条件
     */
    private TradeConditionEnum tradeCondition;
    /**
     * 购方
     */
    private ConsumerBO consumer;
    /**
     * 供应商
     */
    private SupplierBO supplier;
    /**
     * 商品列表
     */
    private List<GoodsBO> goodsList;
    /**
     * 附件列表
     */
    private List<String> accessoryList;
    /**
     * 是否显示柜号
     */
    private Boolean showedContainerNo;
    /**
     * 柜号
     */
    private List<ContainerNoBO> containerNoList;

    /**
     * 版本号
     */
    private int version;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 保存草稿
     * @param userBO
     */
    public void save(ApplyUserBO userBO) {
        this.validateDeleted();
        this.validate();
        this.adjust();
        ApplyUserBO.validate(userBO);
        // 只有待提交和已提交可以保存草稿
        if(id != null && !ProformaInvoiceStatusEnum.DRAFT.equals(status) &&
                !ProformaInvoiceStatusEnum.NORMAL.equals(status)) {
            throw new RuntimeException("参数验证不通过");
//            throw FinanceExceptionCode.PROFORMA_INVOICE_CURRENT_STATUS_NOT_COMPILE;
        }
        this.setApplyUser(userBO);
        this.setInvoiceOprDt(new Date());
        // 作废附件
        this.setResultUrl("");
        this.setStatus(ProformaInvoiceStatusEnum.DRAFT);
    }

    /**
     * 提交
     * @param userBO
     */
    public void submit(ApplyUserBO userBO) {
        this.validateDeleted();
        this.validate();
        this.validateAccessoryList();
        this.adjust();
        ApplyUserBO.validate(userBO);
        // 仅第一次创建、草稿状态、已开票状态可以提交
        if (id != null && !ProformaInvoiceStatusEnum.DRAFT.equals(status) &&
                !ProformaInvoiceStatusEnum.NORMAL.equals(status)) {
            throw new RuntimeException("参数验证不通过");
//            throw FinanceExceptionCode.PROFORMA_INVOICE_EXAMINING_ONLY_DRAFT_NORMAL;
        }
        // 作废附件
        this.setResultUrl("");
        // 变更状态为审核中
        this.setStatus(ProformaInvoiceStatusEnum.EXAMINING);
        // 设置申请人
        this.setApplyUser(userBO);
        this.setInvoiceOprDt(new Date());
    }

    /**
     * 审核通过
     * @param userBO
     */
    public void pass(OprUserBO userBO) {
        this.validateDeleted();
        this.validateIdExist();
        this.validate();
        OprUserBO.validate(userBO);
        if (!ProformaInvoiceStatusEnum.EXAMINING.equals(status)) {
            throw new RuntimeException("参数验证不通过");
//            throw FinanceExceptionCode.PROFORMA_INVOICE_OPR_ONLY_EXAMINING;
        }
        this.setOprUser(userBO);
        this.setStatus(ProformaInvoiceStatusEnum.NORMAL);
    }

    /**
     * 审核不通过
     * @param userBO
     */
    public void refused(OprUserBO userBO) {
        this.validateDeleted();
        this.validateIdExist();
        OprUserBO.validate(userBO);
        OprUserBO.validateReason(userBO);
        if (!ProformaInvoiceStatusEnum.EXAMINING.equals(status)) {
            throw new RuntimeException("参数验证不通过");
//            throw FinanceExceptionCode.PROFORMA_INVOICE_OPR_ONLY_EXAMINING;
        }
        this.setOprUser(userBO);
        this.setStatus(ProformaInvoiceStatusEnum.DRAFT);
    }

    /**
     * 作废发票
     */
    public void cancel(OprUserBO userBO) {
        this.validateDeleted();
        this.validateIdExist();
        OprUserBO.validate(userBO);
        if (!ProformaInvoiceStatusEnum.NORMAL.equals(status)) {
            throw new RuntimeException("参数验证不通过");
//            throw FinanceExceptionCode.PROFORMA_INVOICE_CANCEL_ONLY_NORMAL;
        }
        // 作废附件
        this.setResultUrl("");
        this.setStatus(ProformaInvoiceStatusEnum.CANCEL);
        this.cancelInvoiceNo();
        this.setOprUser(userBO);
    }

    /**
     * 删除发票
     */
    public void delete() {
        this.validateDeleted();
        if (!ProformaInvoiceStatusEnum.DRAFT.equals(status)) {
            throw new RuntimeException("参数验证不通过");
//            throw FinanceExceptionCode.PROFORMA_INVOICE_DELETE_ONLY_DRAFT;
        }
        this.setIsDelete(1);
        this.cancelInvoiceNo();
    }

    /**
     * 矫正填充数据
     * 矫正商品单价、发票合计
     */
    public void adjust() {
        if (!CollectionUtils.isEmpty(this.goodsList)) {
            BigDecimal goodsPriceTotal = new BigDecimal("0");
            for (GoodsBO goodsBO : this.goodsList) {
                goodsBO.adjust();
                goodsPriceTotal = goodsPriceTotal.add(goodsBO.getPriceTotal());
            }
            goodsPriceTotal = goodsPriceTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            this.setPriceTotal(goodsPriceTotal);
        }
    }

    /**
     * 自验证
     */
    public void validate() {
        if (CollectionUtils.isEmpty(this.getGoodsList())) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "商品明细");
        } else {
            GoodsBO.validate(this.getGoodsList());
        }
        if (this.getInvoiceDt() == null) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "发票时间");
        }
        if (this.getCurrency() == null) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "币种");
        }
        if (this.getSupplier() == null) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "供应商");
        } else {
            SupplierBO.validate(this.getSupplier());
        }
        if (this.getTradeCondition() == null) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "贸易条件");
        }
        if (this.getConsumer() == null) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "购方");
        } else {
            ConsumerBO.validate(this.getConsumer());
        }
        if (this.getShowedContainerNo() == null) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "是否显示柜号");
        }
        if (CollectionUtils.isEmpty(this.getContainerNoList())) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "柜号");
        } else {
            this.getContainerNoList().forEach(ContainerNoBO::validate);
        }
    }

    /**
     * 校验附件
     */
    private void validateAccessoryList() {
        if (CollectionUtils.isEmpty(this.accessoryList)) {
            throw new RuntimeException("参数验证不通过");
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "附件");
        }
    }

    /**
     * 验证未删除
     */
    private void validateDeleted() {
        if (new Integer(1).equals(this.isDelete)) {
            throw new RuntimeException("参数验证不通过");
//            throw FinanceExceptionCode.IS_DELETE_ALREADY_NOT_OPR;
        }
    }
    /**
     * 验证id存在
     */
    private void validateIdExist() {
        if (this.id == null) {
            throw new RuntimeException("参数验证不通过");
//            throw FinanceExceptionCode.ID_NOT_EXIST;
        }
    }
    /**
     * 作废发票 编号:  原编号+id
     */
    private void cancelInvoiceNo() {
        this.validateIdExist();
        String flag = "_";
        String appendFlag = flag + this.id.toString();
        // 如果没有作废过，则作废，加个判断，是为了防止重复作废
        if (this.invoiceNo.indexOf(appendFlag) != this.invoiceNo.length() - appendFlag.length()) {
            this.setInvoiceNo(this.invoiceNo + appendFlag);
        }
    }

}
