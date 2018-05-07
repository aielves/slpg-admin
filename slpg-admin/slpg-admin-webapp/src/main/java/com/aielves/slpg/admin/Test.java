package com.aielves.slpg.admin;

import com.aliyun.oss.OSSClient;

import java.io.File;

public class Test {
    static String appid = "";
    static String appkey = "";

    public static void main(String[] args) {
        // endpoint以杭州为例，其它region请按实际情况填写
        String endpoint = "oss-cn-shenzhen.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号
        // 创建OSSClient实例
        OSSClient client = new OSSClient(endpoint, appid, appkey);
        // 上传文件
        client.putObject("aliyunstatic", "1.jpg", new File("C:\\Users\\Administrator\\Pictures\\7.png"));
        // 关闭client
        client.shutdown();
    }

}
