package com.aielves.slpg.admin.controller;

import com.aielves.slpg.admin.aconst.ErrorCode;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.spring.mvc.model.FastMap;
import com.soho.spring.mvc.model.FastView;
import com.soho.spring.shiro.utils.SessionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
public class UserController {

    @RequestMapping("loginInit")
    public Object loginInit() {
        return new FastView("login").done();
    }

    @ResponseBody
    @RequestMapping("login")
    public Object login(String username, String password) throws BizErrorEx {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SessionUtils.login(token, false);
            return new FastMap<>().add("result", "登录成功").add("callurl", "/user/index").done();
        } catch (AuthenticationException e) {
            throw new BizErrorEx(ErrorCode.LOGIN_ERROR, e.getMessage());
        } catch (Exception e) {
            throw new BizErrorEx(ErrorCode.LOGIN_ERROR, "登录系统正繁忙");
        }
    }

    @RequestMapping("index")
    public Object index() {
        return new FastView("index").add("user", SessionUtils.getUser()).done();
    }

    @RequestMapping("logout")
    public Object logout() {
        SessionUtils.logout();
        return new FastView("redirect:/user/index").done();
    }

    @RequestMapping("me/userinfo")
    public Object me_userinfo() {
        return new FastView("me/userinfo").add("user", SessionUtils.getUser()).done();
    }

}