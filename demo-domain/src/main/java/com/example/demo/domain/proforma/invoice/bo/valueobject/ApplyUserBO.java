package com.example.demo.domain.proforma.invoice.bo.valueobject;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 申请人值对象
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class ApplyUserBO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * uid
     */
    private Long uid;
    /**
     * 用户名
     */
    private String userName;

    public static void validate(ApplyUserBO userBO) {
        if (userBO == null || userBO.getUid() == null) {
//            throw new RRException(FinanceExceptionCode.USER_IS_NULL_EXCEPTION, "申请", "id");
            throw new RuntimeException("参数验证不通过");
        }
        if (StringUtils.isEmpty(userBO.getUserName())) {
//            throw new RRException(FinanceExceptionCode.USER_IS_NULL_EXCEPTION, "申请", "用户名");
            throw new RuntimeException("参数验证不通过");
        }
    }

}
