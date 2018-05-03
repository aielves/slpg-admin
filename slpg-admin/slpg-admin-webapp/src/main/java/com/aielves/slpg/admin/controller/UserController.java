package com.aielves.slpg.admin.controller;

import com.aielves.slpg.admin.aconst.ErrorCode;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.spring.mvc.model.FastView;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @RequestMapping("/login")
    public Object login(String username, String password) throws BizErrorEx {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            throw new BizErrorEx(ErrorCode.LOGIN_ERROR, e.getMessage());
        }
        Map<String, String> map = new HashMap<>();
        map.put("sessionId", SecurityUtils.getSubject().getSession().getId().toString());
        // map.put("xss", XSSUtils.strip("<a>test&><"));
        SecurityUtils.getSubject().getSession().getAttribute("test");
        throw new BizErrorEx("0001", "请求错误拉");
        // return map;
    }

    @RequestMapping("/loginInit")
    public Object loginInit() {
        return new FastView("/login").done();
    }

}