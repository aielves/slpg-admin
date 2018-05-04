package com.aielves.slpg.admin.shiro.realm;

import com.aielves.slpg.domain.SlpgRole;
import com.aielves.slpg.domain.SlpgUser;
import com.aielves.slpg.service.SlpgRoleService;
import com.aielves.slpg.service.SlpgUserRoleService;
import com.aielves.slpg.service.SlpgUserService;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.mybatis.sqlcode.condition.imp.SQLCnd;
import com.soho.spring.model.RGX;
import com.soho.spring.security.EncryptService;
import com.soho.spring.shiro.utils.SessionUtils;
import com.soho.spring.utils.RGXUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ShiroAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private SlpgUserService slpgUserService;
    @Autowired
    private SlpgRoleService slpgRoleService;
    @Autowired
    private SlpgUserRoleService slpgUserRoleService;
    @Autowired
    private EncryptService encryptService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(SessionUtils.getUserRoles());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new AuthenticationException("账号/密码不能为空");
        }
        if (!RGXUtils.matches(username, RGX.ACCOUNT) || !RGXUtils.matches(password, RGX.PASSWORD)) {
            throw new AuthenticationException("账号/密码错误");
        }
        try {
            String md5password = encryptService.md5(password);
            SlpgUser user = slpgUserService.findOneByCnd(new SQLCnd().eq("username", username).eq("password", md5password));
            if (user == null) {
                throw new AuthenticationException("账号/密码错误");
            }
            validState(user.getState());
            SessionUtils.setUser(user, username);
            SessionUtils.setUserRoles(hasRoleByUser(user.getId()));
            return new SimpleAuthenticationInfo(token.getUsername(), token.getCredentials(), getName());
        } catch (Exception e) {
            if (e instanceof AuthenticationException) {
                throw (AuthenticationException) e;
            }
        }
        throw new AuthenticationException("登录系统繁忙,请稍后尝试");
    }

    private void validState(Integer state) {
        if (state == null) {
            throw new AuthenticationException("账号尚未开通");
        } else if (state == 1) {
            return;
        } else if (state == 2) {
            throw new AuthenticationException("账号已被冻结");
        } else if (state == 3) {
            throw new AuthenticationException("账号已被禁用");
        } else {
            throw new AuthenticationException("账号状态异常");
        }
    }

    private Set<String> hasRoleByUser(Long userId) throws BizErrorEx {
        List<Long> roleIds = slpgUserRoleService.findFieldByCnd(new SQLCnd().fields("roleId").eq("userId", userId).eq("state", 1), Long.class);
        if (!roleIds.isEmpty()) {
            List<SlpgRole> roleList = slpgRoleService.findByCnd(new SQLCnd().in("id", roleIds).eq("state", 1));
            if (!roleList.isEmpty()) {
                Set roles = new HashSet();
                for (SlpgRole role : roleList) {
                    roles.add(role.getCode());
                }
                return roles;
            }
        }
        return new HashSet<>();
    }

}