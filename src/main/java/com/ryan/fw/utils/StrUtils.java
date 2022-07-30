package com.ryan.fw.utils;

import cn.hutool.core.convert.Convert;
import com.ryan.fw.constans.enums.SepEnum;

import java.util.Arrays;
import java.util.List;

/**
 * @Describe 字符串工具类
 * @Author smileGongJ
 * @Date 2022/7/30 21:05
 */
public class StrUtils {

    /**
     * 获取Long类型集合
     *
     * @param str 英文逗号分隔的字符串 必填
     * @return List<Long>
     */
    public static List<Long> longList(String str) {
        return Arrays.asList(Convert.toLongArray(str.split(SepEnum.COMMA_EN.name())));
    }
}
