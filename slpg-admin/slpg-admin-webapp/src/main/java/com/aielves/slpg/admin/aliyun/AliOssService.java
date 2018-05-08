package com.aielves.slpg.admin.aliyun;

import com.aliyun.oss.OSSClient;
import com.soho.mybatis.exception.BizErrorEx;
import com.soho.spring.model.FileData;
import com.soho.spring.model.OSSData;
import com.soho.spring.model.RetCode;
import com.soho.spring.mvc.model.FastMap;
import com.soho.spring.security.EncryptService;
import com.soho.spring.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class AliOssService {

    @Autowired
    private OSSData ossData;
    @Autowired
    private EncryptService encryptService;

    public FileData uploadFile(MultipartFile multipartFile, Integer maxFileSizeKb, Integer maxWidth, Long userId, boolean thumbnail) throws BizErrorEx {
        FileData fileData = FileUtils.uploadImageByReSize(multipartFile, maxFileSizeKb, maxWidth, userId.toString(), thumbnail);
        String newFileUrl = uploadFile(userId, fileData.getNewFileName(), fileData.getNewFilePath());
        String newFileMD5 = encryptService.md5(userId + newFileUrl);
        fileData.setNewFileUrl(newFileUrl);
        fileData.setNewFileMD5(newFileMD5);
        if (fileData.getReFileName() != null) {
            String reFileUrl = uploadFile(userId, fileData.getReFileName(), fileData.getReFilePath());
            String reFileMD5 = encryptService.md5(userId + reFileUrl);
            fileData.setReFileUrl(reFileUrl);
            fileData.setReFileMD5(reFileMD5);
        }
        return fileData;
    }

    public Object deleteFile(Long userId, String fileUrl, String sign) throws BizErrorEx {
        if (StringUtils.isEmpty(fileUrl)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_MESSAGE, "图片地址不能为空");
        }
        String md5 = encryptService.md5(userId + fileUrl);
        if (!md5.equals(sign)) {
            throw new BizErrorEx(RetCode.BIZ_ERROR_MESSAGE, "图片签名校验失败");
        }
        OSSClient client = new OSSClient(ossData.getEndpoint(), encryptService.aes_d(ossData.getAppId()), encryptService.aes_d(ossData.getAppKey()));
        client.deleteObject(ossData.getBucketName(), fileUrl.replaceAll(ossData.getDomain(), ""));
        client.shutdown();
        return new FastMap<>().add("result", "删除成功").done();
    }

    private String uploadFile(Long userId, String fileName, String filePath) {
        OSSClient client = new OSSClient(ossData.getEndpoint(), encryptService.aes_d(ossData.getAppId()), encryptService.aes_d(ossData.getAppKey()));
        String key = new StringBuffer().append(userId).append("/").append(fileName).toString();
        client.putObject(ossData.getBucketName(), key, new File(filePath));
        client.shutdown();
        return new StringBuffer(ossData.getDomain()).append(key).toString();
    }

}
