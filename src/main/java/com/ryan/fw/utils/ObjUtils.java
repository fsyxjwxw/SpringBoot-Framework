package com.ryan.fw.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.ryan.fw.config.DozerBeanMapperConfig;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Ryan
 * @date 2022/7/27 17:47
 * @description 对象工具类
 */
public class ObjUtils {

    @Resource
    private static Mapper dozerBeanMapper;

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
     *
     * @param obj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convert(Object obj, Class<T> clazz) {
        return DozerBeanMapperConfig.getInstance().map(obj, clazz);
    }

    /**
     * Object转List
     *
     * @param obj   Object对象
     * @param clazz 实体类
     * @param <T>   泛型
     * @return List<T>
     */
    public static <T> List<T> toList(Object obj, Class<T> clazz) {
        if (Objects.isNull(obj)) {
            return new ArrayList<>();
        }
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(convert(o,clazz));
            }
            return result;
        }
        return null;
    }

    /**
     * map转换为Object判空
     *
     * @param map   map对象
     * @param key   key
     * @param clazz Object
     * @param <T>   泛型
     * @return 泛型
     */
    public static <T> T toObject(Map<String, Object> map, String key, Class<T> clazz) {
        Object o = map.get(key);
        return Objects.nonNull(o) ? (T) o : null;
    }

    /**
     * LinkedHashMap类型Object转自定义实体
     *
     * @param o     对象
     * @param clazz 类
     * @param <T>   泛型
     * @return <T>
     */
    public static <T> T toT(Object o, Class<T> clazz) {
        Map<String, Object> map = JSONObject.parseObject(JSON.toJSONString(o));
        return JSON.parseObject(String.valueOf(new JSONObject(map)), clazz);
    }

    /**
     * 对象转换map
     *
     * @param obj 任意对象
     * @return Map
     */
    public static Map<String, Object> objToMap(Object obj) {
        Map<String, Object> map = JSONObject.parseObject(JSON.toJSONString(obj));
        return map;
    }

    /**
     * 仅根据StringKey进行排序
     *
     * @param map 数据集合
     * @param isReverse 是否倒序，true-->是，false-->否
     * @return Map<String, Object>
     */
    public static Map<String, Object> sortOnlyByStringKey(Map<String, Object> map, Boolean isReverse){
        Map<String, Object> sortMap = new HashMap<>(map.size());
        if (isReverse) {
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Object>comparingByKey().reversed())
                    .forEachOrdered(e -> sortMap.put(e.getKey(),e.getValue()));
        } else {
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(e -> sortMap.put(e.getKey(),e.getValue()));
        }
        return sortMap;
    }

}
