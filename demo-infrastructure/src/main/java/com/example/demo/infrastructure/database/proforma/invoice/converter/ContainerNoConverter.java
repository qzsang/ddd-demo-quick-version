package com.example.demo.infrastructure.database.proforma.invoice.converter;

import com.example.demo.domain.proforma.invoice.bo.valueobject.ContainerNoBO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/20
 */
public class ContainerNoConverter {
    public final static String SPLIT_FLAG = "/";


    public static List<ContainerNoBO> toListBO(String containerNoList) {
        if (StringUtils.isEmpty(containerNoList)) {
            return new ArrayList<>();
        }
        String[] noArray = containerNoList.split(SPLIT_FLAG);
        List<ContainerNoBO> list = new ArrayList<>(noArray.length);
        for (String containerNo : noArray) {
            ContainerNoBO containerNoBO = new ContainerNoBO();
            containerNoBO.setContainerNo(containerNo);
            list.add(containerNoBO);
        }
        return list;
    }

    public static String toListString(List<ContainerNoBO> list) {
        StringBuffer stringBuffer = new StringBuffer();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(containerNoBO -> {
                if (!StringUtils.isEmpty(containerNoBO.getContainerNo())) {
                    if (stringBuffer.length() > 0) {
                        stringBuffer.append(SPLIT_FLAG);
                    }
                    stringBuffer.append(containerNoBO.getContainerNo());
                }
            });
        }
        return stringBuffer.toString();
    }

}
