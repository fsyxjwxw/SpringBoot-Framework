package com.ryan.fw.constans.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @Describe 星期
 * @Author smileGongJ
 * @Date 2022/7/30 20:34
 */
@Getter
public enum WeekEnum {

    /**
     * 星期一
     */
    MON(1, "周一", "礼拜一", "星期一", "mon"),

    /**
     * 星期二
     */
    TUE(2, "周二", "礼拜二", "星期二", "tue"),

    /**
     * 星期三
     */
    WED(3, "周三", "礼拜三", "星期三", "wed"),

    /**
     * 星期四
     */
    THUR(4, "周四", "礼拜四", "星期四", "thur"),

    /**
     * 星期五
     */
    FRI(5, "周五", "礼拜五", "星期五", "fri"),

    /**
     * 星期六
     */
    SAT(6, "周六", "礼拜六", "星期六", "sat"),

    /**
     * 星期日
     */
    SUN(7, "周日", "礼拜日", "星期日", "sun"),

    ;

    private final Integer key;

    private final String name1;

    private final String name2;

    private final String name3;

    private final String value;

    WeekEnum(Integer key, String name1, String name2, String name3, String value) {
        this.key = key;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.value = value;
    }

    /**
     * 根据key获取枚举类
     *
     * @param key key值
     * @return WeekEnum
     */
    public static WeekEnum getEnumByKey(Integer key) {
        WeekEnum resultTypeEnum = null;
        for (WeekEnum typeEnum : WeekEnum.values()) {
            if (Objects.equals(key, typeEnum.key)) {
                resultTypeEnum = typeEnum;
                break;
            }
        }
        return resultTypeEnum;
    }

}
