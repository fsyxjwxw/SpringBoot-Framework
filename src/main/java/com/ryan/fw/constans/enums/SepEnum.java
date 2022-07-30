package com.ryan.fw.constans.enums;

import lombok.Getter;

/**
 * @Describe 分隔符
 * @Author smileGongJ
 * @Date 2022/7/30 20:19
 */
@Getter
public enum SepEnum {

    /**
     * 英文逗号
     */
    COMMA_EN(",", "英文逗号"),

    /**
     * 中文逗号
     */
    COMMA_CN("，", "中文逗号"),

    /**
     * 中文顿号
     */
    PAUSE_CN("、", "中文顿号"),

    /**
     * 中文扩号左
     */
    EXTENSION_CN_LEFT("（", "中文扩号左"),

    /**
     * 中文扩号右
     */
    EXTENSION_CN_RIGHT("）", "中文扩号右"),

    /**
     * 竖线
     */
    VERTICAL_LINE("|", "竖线"),

    /**
     * 英文横线
     */
    HORIZONTAL_LINE_EN("-", "英文横线"),

    /**
     * 英文冒号
     */
    COLON_EN(":", "英文冒号"),

    ;

    SepEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private final String name;

    private final String value;

}
