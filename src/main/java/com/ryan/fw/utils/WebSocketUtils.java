package com.ryan.fw.utils;

import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.Session;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;

/**
 * @author Ryan
 * @date 2022/8/1 17:33
 * @description WebSocket工具类
 */
public class WebSocketUtils {
    public static String getRemoteAddress(Session session) {
        if (session == null) {
            return null;
        }
        Async async = session.getAsyncRemote();

        //在Tomcat 8.5以上版本有效
        InetSocketAddress addr = (InetSocketAddress) getFieldInstance(async,"base#socketWrapper#socket#sc#remoteAddress");
        String[] arr = String.valueOf(addr).split(":");
        if (arr.length > 0){
            return arr[0];
        }else{
            return null;
        }
    }

    private static Object getFieldInstance(Object obj, String fieldPath) {
        String[] fields = fieldPath.split("#");
        for (String field : fields) {
            obj = getField(obj, obj.getClass(), field);
            if (obj == null) {
                return null;
            }
        }

        return obj;
    }

    private static Object getField(Object obj, Class<?> clazz, String fieldName) {
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field field;
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(obj);
            } catch (Exception e) {
            }
        }
        return null;
    }
}
