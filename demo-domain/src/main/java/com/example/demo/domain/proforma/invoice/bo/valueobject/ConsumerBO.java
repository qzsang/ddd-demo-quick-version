package com.example.demo.domain.proforma.invoice.bo.valueobject;


import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 购方值对象
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class ConsumerBO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 名字
     */
    private String name;
    /**
     * 地址
     */
    private String addr;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 传真
     */
    private String fax;

    public static void validate(ConsumerBO consumerBO) {
        if(consumerBO == null || StringUtils.isEmpty(consumerBO.getName())) {
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "购方姓名");
            throw new RuntimeException("参数验证不通过");
        }
        if (StringUtils.isEmpty(consumerBO.getAddr())) {
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "购方姓名");
            throw new RuntimeException("参数验证不通过");

        }
    }
}
