package com.ryan.fw.constans.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Describe 单位
 * @Author smileGongJ
 * @Date 2022/7/30 20:32
 */
@Getter
public enum UnitEnum {

    /**
     * 小时
     */
    HOUR(1, "小时"),

    /**
     * 分钟
     */
    MINIS(2, "分钟"),

    ;

    private final Integer key;

    private final String value;

    UnitEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 根据key获取value
     *
     * @param key key值
     * @return String
     */
    public static String of(Integer key) {
        return toMap().get(key);
    }

    /**
     * 获取枚举类map集合
     *
     * @return Map<Integer, String>
     */
    public static Map<Integer, String> toMap() {
        return Arrays.stream(values()).collect(Collectors.toMap(n -> n.key, n -> n.value));
    }

}
