package com.aielves.platform.slpg.bizview.controller;

import com.aielves.platform.rbac.domain.RbacUser;
import com.aielves.platform.slpg.aliyun.AliOssService;
import com.aielves.platform.slpg.bizview.pojo.RbacUserVO;
import com.aielves.platform.slpg.bizview.service.AdmRbacUserService;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.spring.model.FileData;
import com.soho.spring.mvc.annotation.FormToken;
import com.soho.spring.mvc.model.FastMap;
import com.soho.spring.mvc.model.FastView;
import com.soho.spring.shiro.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class AdmRbacUserController {

    @Autowired
    private AdmRbacUserService admRbacUserService;
    @Autowired
    private AliOssService aliOssService;

    // @KillRobot
    @RequestMapping("/loginInit")
    public Object loginInit() {
        return new FastView("/login").done();
    }

    @RequestMapping("/signupInit")
    public Object signupInit() {
        return new FastView("/signup").done();
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(RbacUserVO vo) throws BizErrorEx {
        return admRbacUserService.login(vo);
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

    @RequestMapping("/editMyInit")
    public Object me_myself() {
        return new FastView("/user/editMy").add("user", SessionUtils.getUser()).done();
    }

    @FormToken
    @ResponseBody
    @RequestMapping("/editMy")
    public Object me_myself_edit(RbacUserVO vo) throws BizErrorEx {
        return admRbacUserService.editMy(vo);
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

    @RequestMapping("/list")
    public Object list(RbacUserVO vo) throws BizErrorEx {
        return admRbacUserService.list(vo);
    }

    @RequestMapping("/find")
    public Object find(RbacUserVO vo) throws BizErrorEx {
        return admRbacUserService.find(vo);
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(RbacUserVO vo) throws BizErrorEx {
        return admRbacUserService.save(vo);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(RbacUserVO vo) throws BizErrorEx {
        return admRbacUserService.delete(vo);
    }

}