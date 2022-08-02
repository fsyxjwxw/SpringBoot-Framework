package com.ryan.fw.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author smileGongJ
 * @Description 需要设置属性数据值注解
 * @date 2022/8/2 17:28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedSetFiledValue {
}
