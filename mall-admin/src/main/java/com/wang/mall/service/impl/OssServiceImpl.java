package com.wang.mall.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.wang.mall.dto.OssCallbackParam;
import com.wang.mall.dto.OssCallbackResult;
import com.wang.mall.dto.OssPolicyResult;
import com.wang.mall.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 王念
 * @create 2020-02-16 19:05
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {
    @Value("${aliyun.oss.policy.expire}")
    private int ALIYUN_OSS_EXPIRE;
    @Value("${aliyun.oss.maxSize}")
    private int ALIYUN_OSS_MAX_SIZE;
    @Value("${aliyun.oss.callback}")
    private String ALIYUN_OSS_CALLBACK;
    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKET_NAME;
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.dir.prefix}")
    private String ALIYUN_OSS_DIR_PREFIX;

    @Autowired
    private OSSClient ossClient;

    @Override
    public OssPolicyResult policy() {
        OssPolicyResult ossPolicyResult = null;
        //存储目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dir = ALIYUN_OSS_DIR_PREFIX + sdf.format(new Date());
        //有效期
        long expireEndTime = System.currentTimeMillis() + ALIYUN_OSS_EXPIRE * 1000;
        Date expiration = new Date(expireEndTime);
        //文件大小
        long maxSize = ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
        //回调
        OssCallbackParam ossCallbackParam = OssCallbackParam.builder()
                .callbackUrl(ALIYUN_OSS_CALLBACK)
                .callbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}")
                .callbackBodyType("application/x-www-form-urlencoded")
                .build();
        //提交节点
        String action = "http://" + ALIYUN_OSS_BUCKET_NAME + "." + ALIYUN_OSS_ENDPOINT;
        try {
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
            byte[] binaryData = postPolicy.getBytes("UTF-8");
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient.calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(ossCallbackParam).toString().getBytes("utf-8"));
            //返回结果处理
            ossPolicyResult = OssPolicyResult.builder()
                    .accessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId())
                    .policy(policy)
                    .signature(signature)
                    .dir(dir)
                    .callback(callbackData)
                    .host(action)
                    .build();
        } catch (Exception e) {
            log.error("生成签名失败", e);
        }
        return ossPolicyResult;
    }

    @Override
    public OssCallbackResult callback(HttpServletRequest request) {
        String filename = request.getParameter("filename");
        filename = "http://".concat(ALIYUN_OSS_BUCKET_NAME).concat(".").concat(ALIYUN_OSS_ENDPOINT).concat("/").concat(filename);
        return OssCallbackResult.builder()
                .filename(filename)
                .size(request.getParameter("size"))
                .mimeType(request.getParameter("mimeType"))
                .width(request.getParameter("width"))
                .height(request.getParameter("height"))
                .build();
    }
}
