package com.ryan.fw.constans.enums;

import lombok.Getter;

/**
 * @Describe 异常码
 * @Author smileGongJ
 * @Date 2022/7/30 20:44
 */
@Getter
public enum ExCodeEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * 系统异常
     */
    SYSTEM_BUSY(500, "系统繁忙~请稍后再试~"),
    /**
     * 微服务下线
     */
    SYSTEM_TIMEOUT(501, "系统维护中~请稍后再试~"),
    /**
     * 参数异常
     */
    ILLEGAL_ARGUMENT(400, "无效参数异常"),

    /**
     * 不支持的请求类型
     */
    METHOD_NOT_ALLOWED(405, "不支持当前请求类型"),

    /**
     * 请求类型异常
     */
    MEDIA_TYPE_EX(403, "请求类型异常"),

    /**
     * 空指针异常
     */
    NULL_POINT_EX(500, "空指针异常"),

    /**
     * SQL 运行异常
     */
    SQL_EX(505, "运行SQL出现异常"),

    /**
     * JSON解析异常
     */
    JSON_PARSE_ERROR(506, "JSON解析异常"),

    /**
     * 无效令牌
     */
    JWT_BASIC_INVALID(40000, "无效的身份验证令牌"),

    /**
     * Token失效
     */
    JWT_TOKEN_EXPIRED(40001, "会话超时，请重新登录"),

    /**
     * 登录设备过多
     */
    LOGIN_DEVICE_TOO_MUCH(40001, "登录设备过多，您已被挤下线"),

    /**
     * Token 错误
     */
    JWT_SIGNATURE(40002, "token 值不合法"),

    /**
     * Token 值为空
     */
    JWT_ILLEGAL_ARGUMENT(40003, "缺少token参数"),

    /**
     * 解析Token出现异常
     */
    JWT_PARSER_TOKEN_FAIL(40004, "解析用户身份错误，请重新登录！"),

    /**
     * 无签名参数
     */
    SIGN_NONE(50002, "缺少sign参数"),

    /**
     * app_id
     */
    APP_ID_NONE(50003, "缺少app_id参数"),

    /**
     * app_id 错误
     */
    APP_KID_WRONG(50004, "无效的app_id参数"),

    /**
     * 无时间戳字段
     */
    TIMESTAMP_NONE(50005, "缺少timestamp参数"),

    /**
     * 请求过期
     */
    TIME_STAMP_EXPIRE(50006, "请求过期，timestamp非当前时间"),

    /**
     * 无随机字符串
     */
    NONCE_STR_NONE(50007, "缺少nonce_str参数"),

    /**
     * 签名错误
     */
    SIGN_WRONG(50008, "签名错误"),

    /**
     * 缺少请求用户信息
     */
    LACK_REQUEST_USER_ID(50009, "缺少请求用户信息【ID】"),

    ;

    private final Integer code;
    private String message;

    ExCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExCodeEnum build(String message, Object... param) {
        this.message = String.format(message, param);
        return this;
    }

    public ExCodeEnum param(Object... param) {
        message = String.format(message, param);
        return this;
    }

}
