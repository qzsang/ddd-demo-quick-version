package com.example.demo.infrastructure.database.proforma.invoice.converter;

import com.example.demo.domain.proforma.invoice.bo.valueobject.OprUserBO;
import lombok.Data;

/**
 * @author qzs
 * @email 631706099@qq.com
 * @date 2021/5/18
 */
@Data
public class OprUserConverter {

    public static OprUserBO toBO(Long uid, String username, String reason) {
        OprUserBO applyUserBO = new OprUserBO();
        applyUserBO.setUid(uid);
        applyUserBO.setUserName(username);
        applyUserBO.setReason(reason);
        return applyUserBO;
    }
}
