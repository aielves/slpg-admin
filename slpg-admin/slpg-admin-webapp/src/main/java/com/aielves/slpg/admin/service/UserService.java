package com.aielves.slpg.admin.service;

import com.aielves.slpg.domain.SlpgUser;
import com.soho.mybatis.exception.BizErrorEx;

/**
 * @author shadow
 * @create 2018.5.6
 */
public interface UserService {

    // 用户账号密码登录
    public Object login(String username, String password, boolean rememberMe) throws BizErrorEx;

    // 修改个人账号信息
    public Object myself_edit(SlpgUser user) throws BizErrorEx;

}
