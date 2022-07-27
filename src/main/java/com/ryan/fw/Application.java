package com.ryan.fw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ryan
 * @date 2022-07-27
 * @description 启动类
 */
@SpringBootApplication
@MapperScan("com.ryan.fw.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
