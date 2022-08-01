//package com.ryan.fw.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.BoundListOperations;
//import org.springframework.data.redis.core.RedisConnectionUtils;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Describe Redis缓存
// * @Author smileGongJ
// * @Date 2022/7/30 21:01
// */
//@Component
//public class RedisUtils {
//
//    @Resource
//    RedisTemplate<String, Object> redisTemplate;
//
//    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//    /** 释放连接池资源*/
//    public void backRedis() {
//        RedisConnectionUtils.unbindConnection(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
//    }
//
//    /**
//     * 指定缓存失效时间
//     *
//     * @param key  键
//     * @param time 时间(秒)
//     * @return
//     */
//    public boolean expire(String key, long time) {
//        try {
//            if (time > 0) {
//                redisTemplate.expire(key, time, TimeUnit.SECONDS);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 根据key 获取过期时间
//     *
//     * @param key 键 不能为null
//     * @return 时间(秒) 返回0代表为永久有效
//     */
//    public long getExpire(String key) {
//        try {
//            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 判断key是否存在
//     *
//     * @param key 键
//     * @return true 存在 false不存在
//     */
//    public boolean hasKey(String key) {
//        try {
//            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 删除一个缓存
//     *
//     * @param key 可以传一个值
//     */
//    public void delOne(String key) {
//        try {
//            redisTemplate.delete(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 删除多个缓存
//     *
//     * @param keys
//     */
//    public void delKeys(List<String> keys) {
//        try {
//            redisTemplate.delete(keys);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            backRedis();
//        }
//    }
//
//    //***************** String **********************
//
//    /**
//     * 普通缓存获取
//     *
//     * @param key 键
//     * @return 值
//     */
//    public Object get(String key) {
//        try {
//            return key == null ? null : redisTemplate.opsForValue().get(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 普通缓存放入
//     *
//     * @param key   键
//     * @param value 值
//     * @return true成功 false失败
//     */
//    public boolean set(String key, Object value) {
//        try {
//            redisTemplate.opsForValue().set(key, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 普通缓存放入并设置时间
//     *
//     * @param key   键
//     * @param value 值
//     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
//     * @return true成功 false 失败
//     */
//    public boolean set(String key, Object value, long time) {
//        try {
//            if (time > 0) {
//                redisTemplate.opsForValue().set(key, value, time, TimeUnit.DAYS);
//            } else {
//                set(key, value);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//
//    /**
//     * 递增
//     *
//     * @param key   键
//     * @param delta 要增加几(大于0)
//     * @return
//     */
//    public long incr(String key, long delta) {
//        if (delta < 0) {
//            throw new RuntimeException("递增因子必须大于0");
//        }
//        try {
//            return redisTemplate.opsForValue().increment(key, delta);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 递减
//     *
//     * @param key   键
//     * @param delta 要减少几(小于0)
//     * @return
//     */
//    public long decr(String key, long delta) {
//        if (delta < 0) {
//            throw new RuntimeException("递减因子必须大于0");
//        }
//        try {
//            return redisTemplate.opsForValue().increment(key, -delta);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//
//    //============================== Hash ===============================
//
//    /**
//     * HashGet
//     *
//     * @param key  键 不能为null
//     * @param item 项 不能为null
//     * @return 值
//     */
//    public Object hGet(String key, String item) {
//        try {
//            return redisTemplate.opsForHash().get(key, item);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 获取hashKey对应的所有键值
//     *
//     * @param key 键
//     * @return 对应的多个键值
//     */
//    public Map<Object, Object> hmGet(String key) {
//        try {
//            return redisTemplate.opsForHash().entries(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * HashSet
//     *
//     * @param key 键
//     * @param map 对应多个键值
//     * @return true 成功 false 失败
//     */
//    public boolean hMapSet(String key, Map<String, Object> map) {
//        try {
//            redisTemplate.opsForHash().putAll(key, map);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * HashSet 并设置时间
//     *
//     * @param key  键
//     * @param map  对应多个键值
//     * @param time 时间(秒)
//     * @return true成功 false失败
//     */
//    public boolean hmSetTime(String key, Map<String, Object> map, long time) {
//        try {
//            redisTemplate.opsForHash().putAll(key, map);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 向一张hash表中放入数据,如果不存在将创建
//     *
//     * @param key   键
//     * @param item  项
//     * @param value 值
//     * @return true 成功 false失败
//     */
//    public boolean hSave(String key, String item, Object value) {
//        try {
//            redisTemplate.opsForHash().put(key, item, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 向一张hash表中放入数据,如果不存在将创建
//     *
//     * @param key   键
//     * @param item  项
//     * @param value 值
//     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
//     * @return true 成功 false失败
//     */
//    public boolean hSaveTime(String key, String item, Object value, long time) {
//        try {
//            redisTemplate.opsForHash().put(key, item, value);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 删除hash表中的值
//     *
//     * @param key  键 不能为null
//     * @param item 项 可以使多个 不能为null
//     */
//    public void hDel(String key, Object item) {
//        try {
//            redisTemplate.opsForHash().delete(key, item);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 判断hash表中是否有该项的值
//     *
//     * @param key  键 不能为null
//     * @param item 项 不能为null
//     * @return true 存在 false不存在
//     */
//    public boolean hHasKey(String key, String item) {
//        try {
//            return redisTemplate.opsForHash().hasKey(key, item);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
//     *
//     * @param key  键
//     * @param item 项
//     * @param by   要增加几(大于0)
//     * @return
//     */
//    public double hIncr(String key, String item, double by) {
//        try {
//            return redisTemplate.opsForHash().increment(key, item, by);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * hash递减
//     *
//     * @param key  键
//     * @param item 项
//     * @param by   要减少记(小于0)
//     * @return
//     */
//    public double hDecr(String key, String item, double by) {
//        try {
//            return redisTemplate.opsForHash().increment(key, item, -by);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//
//    //=========================== set ============================
//
//    /**
//     * 根据key获取Set中的所有值
//     *
//     * @param key 键
//     * @return
//     */
//    public Set<Object> sGet(String key) {
//        try {
//            return redisTemplate.opsForSet().members(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 根据value从一个set中查询,是否存在
//     *
//     * @param key   键
//     * @param value 值
//     * @return true 存在 false不存在
//     */
//    public boolean sHasKey(String key, Object value) {
//        try {
//            return redisTemplate.opsForSet().isMember(key, value);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 将数据放入set缓存
//     *
//     * @param key    键
//     * @param values 值 可以是多个
//     * @return 成功个数
//     */
//    public long sSet(String key, Object values) {
//        try {
//            return redisTemplate.opsForSet().add(key, values);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 将set数据放入缓存
//     *
//     * @param key    键
//     * @param time   时间(秒)
//     * @param values 值 可以是多个
//     * @return 成功个数
//     */
//    public long sSetAndTime(String key, long time, Object values) {
//        try {
//            Long count = redisTemplate.opsForSet().add(key, values);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return count;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 获取set缓存的长度
//     *
//     * @param key 键
//     * @return
//     */
//    public long sGetSetSize(String key) {
//        try {
//            return redisTemplate.opsForSet().size(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 移除值为value的
//     *
//     * @param key    键
//     * @param values 值 可以是多个
//     * @return 移除的个数
//     */
//    public long setRemove(String key, Object values) {
//        try {
//            Long count = redisTemplate.opsForSet().remove(key, values);
//            return count;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//    //===============================list=================================
//
//    /**
//     * 获取list缓存的内容
//     *
//     * @param key   键
//     * @param start 开始
//     * @param end   结束  0 到 -1代表所有值
//     * @return
//     */
//    public List<Object> lGet(String key, long start, long end) {
//        try {
//            return redisTemplate.opsForList().range(key, start, end);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 获取list缓存的长度
//     *
//     * @param key 键
//     * @return
//     */
//    public long lGetListSize(String key) {
//        try {
//            return redisTemplate.opsForList().size(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 通过索引 获取list中的值
//     *
//     * @param key   键
//     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
//     * @return
//     */
//    public Object lGetIndex(String key, long index) {
//        try {
//            return redisTemplate.opsForList().index(key, index);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 将list放入缓存
//     *
//     * @param key   键
//     * @param value 值
//     * @return
//     */
//    public boolean lpush(String key, Object value) {
//        try {
//            redisTemplate.opsForList().rightPush(key, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 将list放入缓存
//     *
//     * @param key   键
//     * @param value 值
//     * @param time  时间(秒)
//     * @return
//     */
//    public boolean lpushTime(String key, Object value, long time) {
//        try {
//            redisTemplate.opsForList().rightPush(key, value);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 将list放入缓存
//     *
//     * @param key   键
//     * @param value 值
//     * @return
//     */
//    public boolean lpushAll(String key, List value) {
//        try {
//            redisTemplate.opsForList().rightPushAll(key, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 将list放入缓存
//     *
//     * @param key   键
//     * @param value 值
//     * @param time  时间(秒)
//     * @return
//     */
//    public boolean lpushAllTime(String key, List<Object> value, long time) {
//        try {
//            redisTemplate.opsForList().rightPushAll(key, value);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 根据索引修改list中的某条数据
//     *
//     * @param key   键
//     * @param index 索引
//     * @param value 值
//     * @return
//     */
//    public boolean lUpdateIndex(String key, long index, Object value) {
//        try {
//            redisTemplate.opsForList().set(key, index, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 移除N个值为value
//     *
//     * @param key   键
//     * @param count 移除多少个
//     * @param value 值
//     * @return 移除的个数
//     */
//    public long lRemove(String key, long count, Object value) {
//        try {
//            Long remove = redisTemplate.opsForList().remove(key, count, value);
//            return remove;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 模糊查询获取key值
//     *
//     * @param pattern
//     * @return
//     */
//    public Set<?> keys(String pattern) {
//        try {
//            return redisTemplate.keys(pattern);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            backRedis();
//        }
//    }
//
//    /**
//     * 使用Redis的消息队列
//     *
//     * @param channel
//     * @param message 消息内容
//     */
//    public void convertAndSend(String channel, Object message) {
//        try {
//            redisTemplate.convertAndSend(channel, message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            backRedis();
//        }
//    }
//
//    //=========BoundListOperations 用法 start============
//
//    /**
//     * 将数据添加到Redis的list中（从右边添加）
//     *
//     * @param listKey
//     * @param values  待添加的数据
//     */
//    public void addToListRight(String listKey, Object values) {
//        //绑定操作
//        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
//        //插入数据
//        boundValueOperations.rightPushAll(values);
//        //设置过期时间
//
//    }
//
//    /**
//     * 根据起始结束序号遍历Redis中的list
//     *
//     * @param listKey
//     * @param start   起始序号
//     * @param end     结束序号
//     * @return
//     */
//    public List<Object> rangeList(String listKey, long start, long end) {
//        //绑定操作
//        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
//        //查询数据
//        return boundValueOperations.range(start, end);
//    }
//
//    /**
//     * 弹出右边的值 --- 并且移除这个值
//     *
//     * @param listKey
//     */
//    public Object rifhtPop(String listKey) {
//        //绑定操作
//        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
//        return boundValueOperations.rightPop();
//    }
//
//}
