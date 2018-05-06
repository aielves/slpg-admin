package com.aielves.slpg.admin.controller;

import com.aielves.slpg.admin.service.UserService;
import com.aielves.slpg.domain.SlpgUser;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.spring.mvc.model.FastMap;
import com.soho.spring.mvc.model.FastView;
import com.soho.spring.shiro.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/loginInit")
    public Object loginInit() {
        return new FastView("/login").done();
    }

    @ResponseBody
    @RequestMapping("/login")
    public Object login(String username, String password, boolean rememberMe) throws BizErrorEx {
        return userService.login(username, password, rememberMe);
    }

    @RequestMapping("/index")
    public Object index() {
        return new FastView("/index").add("user", SessionUtils.getUser()).done();
    }

    @RequestMapping("/logout")
    public Object logout() {
        SessionUtils.logout();
        return new FastView("redirect:/user/index").done();
    }

    @RequestMapping("/me/myself")
    public Object me_myself() {
        return new FastView("/me/myself").add("user", SessionUtils.getUser()).done();
    }

    @ResponseBody
    @RequestMapping("/me/myself_edit")
    public Object me_myself_edit(SlpgUser user) throws BizErrorEx {
        return userService.myself_edit(user);
    }

    @ResponseBody
    @RequestMapping("/file/upload")
    public Object file_upload(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new FastMap<>().add("url", "http://static.cartoonai.com/1.jpg").done();
    }

}