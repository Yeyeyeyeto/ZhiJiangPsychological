package com.eternal.zjp.oss.service;

import java.io.InputStream;

/**
 * @Auther Eternal
 * @Date 2021/10/11
 */
public interface FileService {

    /**
     * 文件上传至阿里云
     */
    String upload(InputStream inputStream, String module, String fileName);

    void removeFile(String url);
}
