package com.ryan.fw.constans.enums;

import lombok.Getter;

/**
 * @Describe 是与否
 * @Author smileGongJ
 * @Date 2022/7/30 20:28
 */
@Getter
public enum YOrNEnum {

    /**
     * 是，表示确认
     */
    Y(1, "是"),

    /**
     * 否，表示否定
     */
    N(0, "否")

    ;

    private final Integer key;

    private final String value;

    YOrNEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

}
