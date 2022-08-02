package com.ryan.fw.aspect;

import cn.hutool.core.convert.Convert;
import com.ryan.fw.annotations.SetValue;
import com.ryan.fw.constans.enums.YOrNEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author smileGongJ
 * @Description 设置属性数据值实体
 * @date 2022/8/2 17:31
 */
@Slf4j
@Aspect
@Component
public class SetFiledValueAspect {

	@Resource
	private ApplicationContext applicationContext;

	/**
	 * 需要设置属性数据值后置通知
	 *
	 * @param ret Object
	 * @return Object
	 */
	@AfterReturning(value = "@annotation(com.ryan.fw.annotations.NeedSetFiledValue)", returning = "ret")
	public Object insertAroundAdvice(JoinPoint jp, Object ret) {
		Long proId = (Long)jp.getArgs()[0];
		Class<?> clazz = ret.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Field targetField = null;
		for (Field field : fields) {
			SetValue annotation = field.getAnnotation(SetValue.class);
			if (Objects.isNull(annotation)) {
				continue;
			}
			field.setAccessible(Boolean.TRUE);
			Object bean = applicationContext.getBean(annotation.beanName());
			try {
				Method method = bean.getClass().getMethod(annotation.method(), annotation.paramType());
				Object value;
				value = method.invoke(bean, proId);
				if (Objects.nonNull(value)) {
					if (Objects.isNull(targetField)) {
						targetField = value.getClass().getDeclaredField(annotation.targetFiled());
						targetField.setAccessible(Boolean.TRUE);
						value = targetField.get(value);
					}
				}
				field.set(ret, Convert.toInt(value).equals(YOrNEnum.Y.getKey()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

}
