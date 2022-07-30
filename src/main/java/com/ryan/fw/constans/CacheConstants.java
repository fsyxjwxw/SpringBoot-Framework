package com.ryan.fw.constans;

/**
 * @Describe 缓存
 * @Author smileGongJ
 * @Date 2022/7/30 20:53
 */
public interface CacheConstants {

    /**
     * oauth 缓存前缀
     */
    String PROJECT_OAUTH_ACCESS = "oauth:access:";

    /**
     * oauth 缓存令牌前缀
     */
    String PROJECT_OAUTH_TOKEN = "oauth:token:";

    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY:";

    /**
     * 菜单信息缓存
     */
    String MENU_DETAILS = "menu_details";

    /**
     * 用户信息缓存
     */
    String USER_DETAILS = "user_details";

    /**
     * 字典信息缓存
     */
    String DICT_DETAILS = "dict_details";

    /**
     * oauth 客户端信息
     */
    String CLIENT_DETAILS_KEY = "oauth:client:details";

    /**
     * 参数缓存
     */
    String PARAMS_DETAILS = "params_details";

    /**
     * 地址省份
     */
    String PROVINCE = "address:province";

    /**
     * 地址城市
     */
    String CITY = "address:city";

    /**
     * 地址区域
     */
    String AREA = "address:area";

    /**
     * 地址代码
     */
    String ADDRESS_CODE ="address:code";

    /**
     * 手机号今日发送短信次数
     */
    String PHONE_CODE_COUNT = "mobile:code:";

    /**
     * IP今日发送短信次数
     */
    String IP_CODE_COUNT = "ip:code:";

}
