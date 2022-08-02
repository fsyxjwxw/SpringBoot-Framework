package com.ryan.fw.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author smileGongJ
 * @Description 设置布尔类型数据值注解
 * @date 2022/8/2 17:20
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SetValue {

	/**
	 * bean实例名称
	 *
	 * @return String
	 */
	Class<?> beanName();

	/**
	 * 方法名称
	 *
	 * @return String
	 */
	String method();

	/**
	 * 参数类型
	 *
	 * @return String
	 */
	Class<?> paramType();

	/**
	 * 目标字段名称
	 *
	 * @return String
	 */
	String targetFiled();

}
