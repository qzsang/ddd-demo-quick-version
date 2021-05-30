package com.example.demo.infrastructure.database.proforma.invoice.converter;

import com.example.demo.domain.proforma.invoice.bo.valueobject.ApplyUserBO;
import lombok.Data;
import java.io.Serializable;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class ApplyUserConverter implements Serializable {

    public static ApplyUserBO toBO(Long uid, String username) {
        ApplyUserBO applyUserBO = new ApplyUserBO();
        applyUserBO.setUid(uid);
        applyUserBO.setUserName(username);
        return applyUserBO;
    }

}
