package com.ryan.fw.utils;

import com.ryan.fw.instance.DozerBean;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    /**
     * Object转List
     *
     * @param obj Object对象
     * @param clazz 实体类
     * @param <T> 泛型
     * @return List<T>
     */
    public static <T> List<T> toList(Object obj, Class<T> clazz) {
        if (Objects.isNull(obj)) {
            return new ArrayList<>();
        }
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    /**
     * map转换为Object判空
     *
     * @param map map对象
     * @param key key
     * @param clazz Object
     * @param <T> 泛型
     * @return 泛型
     */
    public static <T> T toObject(Map<String, Object> map, String key, Class<T> clazz){
        Object o = map.get(key);
        return Objects.nonNull(o) ? (T) o : null;
    }

}
