package com.example.demo.domain.proforma.invoice.bo.valueobject;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 柜号
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class ContainerNoBO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String REGEX = "([A-Za-z]{4}[0-9]{7})|([0-9]{3}-[0-9]{7})";
    /**
     * 柜号
     */
    private String containerNo;

    public static void validate(ContainerNoBO containerNoBO) {
        if (containerNoBO == null || StringUtils.isEmpty(containerNoBO.getContainerNo())) {
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "柜号");
            throw new RuntimeException("参数验证不通过");
        } else if (!Pattern.matches(REGEX, containerNoBO.getContainerNo())){
//            throw new RRException(FinanceExceptionCode.PARAMETER_NOT_MATCH, "柜号");
        }
    }

    public static void validate(List<ContainerNoBO> containerNoBOList) {
        if(CollectionUtils.isEmpty(containerNoBOList)) {
//            throw new RRException(FinanceExceptionCode.PARAMETER_NEED_EXIST_EXCEPTION, "柜号");
            throw new RuntimeException("参数验证不通过");
        } else {
            containerNoBOList.forEach(ContainerNoBO::validate);
        }
    }
}
