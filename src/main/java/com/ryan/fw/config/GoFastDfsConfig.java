package com.ryan.fw.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @apiNote 文件服务器配置类
 */
@Configuration
@ConfigurationProperties(prefix = "gofastdfs.config")
@Data
public class GoFastDfsConfig {
    /**
     * 文件服务器IP地址
     */
    private String ip;

    /**
     * 文件服务器端口
     */
    private String port;

    /**
     * 文件服务器组名
     */
    private String group;

    /**
     * 文件服务器场景
     */
    private String scene;
}
