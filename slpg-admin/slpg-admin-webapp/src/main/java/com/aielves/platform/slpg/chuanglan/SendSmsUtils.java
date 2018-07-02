package com.aielves.platform.slpg.chuanglan;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/10/18.
 */
public class SendSmsUtils {

    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "";
    // 用户平台API密码(非登录密码)
    public static String pswd = "";
    //状态报告
    public static String report = "true";
    // 发送短信地址
    public static String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 短信内容
//        String msg = "【贵州习酒】恭喜您成为我们的会员,您的登录密码是：123456，请妥善保管。";
        String msg = "【SOHO科技】您的密码是：111123，请在登录后修改密码，切勿泄露密码给他人。";
        // 手机号码
        String phone = "13823912543";

        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone, report);

        String requestJson = JSON.toJSONString(smsSingleRequest);

        System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);

        System.out.println("response after request result is :" + response);

        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

        System.out.println("response  toString is :" + smsSingleResponse);


    }


}
