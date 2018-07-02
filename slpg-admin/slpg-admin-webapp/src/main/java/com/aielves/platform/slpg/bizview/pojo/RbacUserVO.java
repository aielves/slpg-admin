package com.aielves.platform.slpg.bizview.pojo;

import com.aielves.platform.rbac.domain.RbacUser;
import com.soho.spring.model.ReqData;

/**
 * Created by shadow on 2018/6/4.
 */
public class RbacUserVO extends ReqData<RbacUser, RbacUser> {

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
