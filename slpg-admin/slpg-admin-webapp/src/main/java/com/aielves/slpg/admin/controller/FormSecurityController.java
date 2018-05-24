package com.aielves.slpg.admin.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.spring.mvc.model.FastView;
import com.soho.spring.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/security")
public class FormSecurityController {

    IAcsClient client = null;
    private String accessKeyId = "LTAI6Lq7SoAzfMwE";
    private String accessKeySecret = "rXMwZH2RqgIRRLl9LipsoT432jAJYU";

    public FormSecurityController() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "afs", "afs.aliyuncs.com");
    }

    @RequestMapping("/ggk/init")
    public Object ggk_init(String callurl) {
        return new FastView("ali/ggk").add("callurl", StringUtils.isEmpty(callurl) ? "" : callurl).done();
    }

    @RequestMapping("/ggk/valid")
    public Object ggk_valid(HttpServletRequest httpRequest, String sessionid, String sig, String token, String scene, String callurl) throws BizErrorEx, ClientException {
        AuthenticateSigRequest request = new AuthenticateSigRequest();
        request.setSessionId(sessionid);// 必填参数，从前端获取，不可更改，android和ios只变更这个参数即可，下面参数不变保留xxx
        request.setSig(sig);// 必填参数，从前端获取，不可更改
        request.setToken(token);// 必填参数，从前端获取，不可更改
        request.setScene(scene);// 必填参数，从前端获取，不可更改
        request.setAppKey("FFFF000000000179AE04");// 必填参数，后端填写
        request.setRemoteIp(HttpUtils.getIpAddr(httpRequest));// 必填参数，后端填写
        try {
            AuthenticateSigResponse response = client.getAcsResponse(request);
            if (response.getCode() == 100) {
                System.out.println("验签通过");
                return new FastView("redirect:" + callurl).done();
            } else {
                System.out.println("验签失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FastView("redirect:/security/ggk/init?callurl=" + callurl).done();
    }

}