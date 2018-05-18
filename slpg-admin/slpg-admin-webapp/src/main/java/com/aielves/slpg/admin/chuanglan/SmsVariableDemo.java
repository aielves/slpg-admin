package com.aielves.slpg.admin.chuanglan;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2018/5/18.
 */
public class SmsVariableDemo {

    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "";
    // 用户平台API密码(非登录密码)
    public static String pswd = "";

    public static void main(String[] args) throws UnsupportedEncodingException {
        //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
        String smsVariableRequestUrl = "http://smssh1.253.com/msg/variable/json";
        //短信内容
        //String msg = "【253云通讯】尊敬的{$var},您好,您的密码是{$var},{$var}分钟内有效";
        //短信内容
        String msg = "【SOHO科技】您的密码是：{$var}，请在登录后修改密码，切勿泄露密码给他人。";
        //参数组
        String params = "13823912543,789789;13823960305,123456;";
        //状态报告
        String report= "true";

        SmsVariableRequest smsVariableRequest=new SmsVariableRequest(account, pswd, msg, params, report);

        String requestJson = JSON.toJSONString(smsVariableRequest);

        System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsVariableRequestUrl, requestJson);

        System.out.println("response after request result is : " + response);

        SmsVariableResponse smsVariableResponse = JSON.parseObject(response, SmsVariableResponse.class);

        System.out.println("response  toString is : " + smsVariableResponse);

    }


}
