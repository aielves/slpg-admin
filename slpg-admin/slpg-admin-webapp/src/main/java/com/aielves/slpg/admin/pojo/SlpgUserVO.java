package com.aielves.slpg.admin.pojo;

import com.aielves.slpg.domain.SlpgUser;
import com.soho.spring.model.ReqData;

/**
 * Created by shadow on 2018/6/4.
 */
public class SlpgUserVO extends ReqData<SlpgUser, SlpgUser> {

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
