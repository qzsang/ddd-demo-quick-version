package com.example.demo.infrastructure.database.proforma.invoice.converter;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/20
 */
public class AccessoryConverter {
    public final static String SPLIT_FLAG = ",";

    public static List<String> toListBO(String containerNoList) {
        if (StringUtils.isEmpty(containerNoList)) {
            return new ArrayList<>();
        }
        String[] noArray = containerNoList.split(SPLIT_FLAG);
        List<String> list = new ArrayList<>(noArray.length);
        for (String accessory : noArray) {
            list.add(accessory);
        }
        return list;
    }


    public static String toListString(List<String> accessoryList) {
        StringBuffer stringBuffer = new StringBuffer();
        if (CollectionUtils.isNotEmpty(accessoryList)) {
            accessoryList.forEach(accessory -> {
                if (!StringUtils.isEmpty(accessory)) {
                    if (stringBuffer.length() > 0) {
                        stringBuffer.append(SPLIT_FLAG);
                    }
                    stringBuffer.append(accessory);
                }
            });
        }
        return stringBuffer.toString();
    }

}
