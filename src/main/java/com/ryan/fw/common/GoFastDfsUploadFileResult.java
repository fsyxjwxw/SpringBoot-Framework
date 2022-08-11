package com.ryan.fw.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 * @apiNote 文件服务器上传文件的返回值
 */
@Data
public class GoFastDfsUploadFileResult implements Serializable {
    /**
     * 文件服务器地址
     */

    private String domain;
    /**
     * 文件的md5值
     */

    private String md5;

    /**
     * 媒体时间
     */
    private String mtime;

    /**
     * 文件服务器存放地址
     */
    private String path;

    /**
     * 回调结果
     */
    private Long retcode;

    /**
     * 回调结果信息
     */
    private String retmsg;

    /**
     * 文件服务器的场景
     */
    private String scene;

    private String scenes;

    /**
     * 文件的大小
     */
    private Long size;

    /**
     * 文件的源地址
     */
    private String src;
    /**
     * 文件查看下载地址
     */
    private String url;
}
