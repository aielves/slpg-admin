package com.aielves.slpg.admin.shiro.realm;

import com.aielves.slpg.domain.SlpgUser;
import com.aielves.slpg.service.SlpgUserService;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.mybatis.sqlcode.condition.imp.SQLCnd;
import com.soho.spring.model.RGX;
import com.soho.spring.shiro.utils.SessionUtils;
import com.soho.spring.utils.RGXUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ShiroAuthorizingRealm extends AuthorizingRealm {

    @Autowired(required = false)
    private SlpgUserService slpgUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("user");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new AuthenticationException("帐号/密码不能为空");
        }
        if (!RGXUtils.matches(username, RGX.ACCOUNT) || !RGXUtils.matches(password, RGX.PASSWORD)) {
            throw new AuthenticationException("帐号/密码错误");
        }
        try {
            SlpgUser user =  slpgUserService.findOneByCnd(new SQLCnd().eq("username", username));
        } catch (BizErrorEx ex) {
            ex.printStackTrace();
        }
        return new SimpleAuthenticationInfo(token.getUsername(), token.getCredentials(), getName());
    }

}