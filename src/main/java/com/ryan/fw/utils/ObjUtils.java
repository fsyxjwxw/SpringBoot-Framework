package com.ryan.fw.utils;

import com.ryan.fw.instance.DozerBean;
import org.dozer.DozerBeanMapper;

import java.util.Objects;

/**
 * @author Ryan
 * @date 2022/7/27 17:47
 * @description 对象工具类
 */
public class ObjUtils {

    /**
     * 验证对象是否为 null
     *
     * @param obj     验证对象
     * @param message 错误消息
     */
    public static void checkNull(Object obj, String message) {
        if (Objects.isNull(obj)) {
            throw new RuntimeException(message);
        }
    }

    /**
     * 对象间转换
     * @param obj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convert(Object obj,Class<T> clazz){
        return DozerBean.getInstance().map(obj, clazz);
    }

}
