package com.aielves.slpg.admin;

import com.aliyun.oss.OSSClient;

import java.io.File;

public class Test {
    static String appid = "1e31f9838fd980f24a60f7540b8f053bb6cf1a742a8dcca814f668cb1ada781f";
    static String appkey = "c2709ed77982e500f1bd1fd29e96a6039e26ef46e62850d79d7788ff05eb4f4c";

    public static void main(String[] args) {
        // endpoint以杭州为例，其它region请按实际情况填写
        String endpoint = "oss-cn-shenzhen.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号
        // 创建OSSClient实例
        OSSClient client = new OSSClient(endpoint, appid, appkey);
        // 上传文件
        // client.putObject("aliyunstatic", "1.jpg", new File("C:\\Users\\aielves\\Pictures\\Camera Roll\\2.jpg"));
        client.deleteObject("aliyunstatic", "1.jpg");
        // 关闭client
        client.shutdown();
    }

}
