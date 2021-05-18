package com.rt.base.framework.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ConfigConstants
 * @Author: 59644
 * @Description: 用于配置不同系统环境常量
 * @Date: 2019-11-4 13:36
 * @Version: 1.0
 */
@Component
public class ConfigConstants {
    /**
     * 文件上传配置信息地址 FILE_SERVER_URL
     */
    private static String fileServerUrl;

    /**
     * 文件上传根目录(盘符) FILE_DIR
     */
    private static String fileDir;

    /**
     * 文件上传路径
     */
    private static String filePath;
    /**
     * 文件磁盘图片映射URL
     */
    private static String mapperUrl;

    /**
     * 域名或者Ip
     */
    private static String dns;


//    @Value("${param.fileServerUrl}")
    public void setFileServerUrl(String fileServerUrl) {
        ConfigConstants.fileServerUrl = fileServerUrl;
    }

    public static String getFileServerUrl() {
        return fileServerUrl;
    }

    @Value("${param.fileDir}")
    public void setFileDir(String fileDir) {
        ConfigConstants.fileDir = fileDir;
    }

    public static String getFileDir() {
        return fileDir;
    }

    @Value("${param.filePath}")
    public void setFilePath(String filePath) {
        ConfigConstants.filePath = filePath;
    }


    public static String getFilePath() {
        return filePath;
    }

    @Value("${param.mapperUrl}")
    public void setMapperUrl(String mapperUrl) {
        ConfigConstants.mapperUrl = mapperUrl;
    }
    public static String getMapperUrl() {
        return mapperUrl;
    }

}
