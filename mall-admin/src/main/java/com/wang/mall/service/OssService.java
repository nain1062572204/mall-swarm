package com.wang.mall.service;

import com.wang.mall.dto.OssCallbackResult;
import com.wang.mall.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 *
 * @author 王念
 * @create 2020-02-16 18:46
 */
public interface OssService {
    /**
     * 上传策略生成
     */
    OssPolicyResult policy();

    /**
     * 上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);

}
