package com.ryan.fw.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ryan
 * @date 2022/8/6 16:56
 * @description 读取JSON配置文件
 */
@Slf4j
public class JsonConfig {

    private static Map<String, List<String>> equipmentMap = new LinkedHashMap<>();

    public static void initConfig() {
        try {
            equipmentMap = JSON.parseObject(new ClassPathResource("/config/Equipment.json").getInputStream(), Map.class);
        } catch (IOException e) {
            log.error("【配置读取】读取Json配置失败");
            e.printStackTrace();
        }
        log.info("【配置读取】初始化Json配置完成");
    }

    public static Map<String, List<String>> getEquipmentMap() {
        return equipmentMap;
    }
}
