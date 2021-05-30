package com.example.demo.domain.proforma.invoice.bo.valueobject;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 审核人值对象
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class OprUserBO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * uid
     */
    private Long uid;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 理由
     */
    private String reason;

    public static void validate(OprUserBO userBO) {
        if (userBO == null || userBO.getUid() == null) {
//            throw new RRException(FinanceExceptionCode.USER_IS_NULL_EXCEPTION, "审核", "id");
            throw new RuntimeException("参数验证不通过");
        }
        if (StringUtils.isEmpty(userBO.getUserName())) {
//            throw new RRException(FinanceExceptionCode.USER_IS_NULL_EXCEPTION, "审核", "用户名");
            throw new RuntimeException("参数验证不通过");
        }
    }

    public static void validateReason(OprUserBO userBO) {
        if (userBO == null ||  StringUtils.isEmpty(userBO.getReason())) {
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "理由");
            throw new RuntimeException("参数验证不通过");
        }
    }
}
