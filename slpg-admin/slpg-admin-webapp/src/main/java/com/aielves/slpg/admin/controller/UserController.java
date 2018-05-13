package com.aielves.slpg.admin.controller;

import com.aielves.slpg.admin.aliyun.AliOssService;
import com.aielves.slpg.admin.service.UserService;
import com.aielves.slpg.domain.SlpgUser;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.spring.model.FileData;
import com.soho.spring.mvc.annotation.FormToken;
import com.soho.spring.mvc.model.FastMap;
import com.soho.spring.mvc.model.FastView;
import com.soho.spring.shiro.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AliOssService aliOssService;

    @RequestMapping("/loginInit")
    public Object loginInit() {
        return new FastView("/login").done();
    }

    @RequestMapping("/signupInit")
    public Object signupInit() {
        return new FastView("/signup").done();
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

    @FormToken
    @ResponseBody
    @RequestMapping("/me/myself_edit")
    public Object me_myself_edit(SlpgUser user) throws BizErrorEx {
        return userService.myself_edit(user);
    }

    @ResponseBody
    @RequestMapping("/file/upload")
    public Object file_upload(MultipartFile file) throws BizErrorEx {
        Long userId = SessionUtils.getUserId();
        FileData fileData = aliOssService.uploadFile(file, 500, 500, userId, false);
        return new FastMap<>().add("url", fileData.getNewFileUrl()).add("sign", fileData.getNewFileMD5()).done();
    }

    @ResponseBody
    @RequestMapping("/file/delete")
    public Object file_delete(String url, String sign) throws BizErrorEx {
        Long userId = SessionUtils.getUserId();
        return aliOssService.deleteFile(userId, url, sign);
    }

}