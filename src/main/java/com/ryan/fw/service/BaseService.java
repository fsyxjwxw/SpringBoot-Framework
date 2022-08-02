package com.ryan.fw.service;

/**
 * @author smileGongJ
 * @Description 基础业务接口
 * @date 2022/8/1 11:27
 */
public interface BaseService {

	/**
	 * 初始化对象
	 *
	 * @return Object
	 */
	Object init();

	/**
	 * 拷贝对象
	 *
	 * @param o1 实体对象
	 * @param o2 实体对象
	 * @return Object
	 */
	Object copyProperties(Object o1, Object o2);

	/**
	 * 校验对象是否为null，为null则抛出异常
	 *
	 * @param o 实体对象
	 * @param msg 异常说明
	 */
	void checkIsNull(Object o, String msg);

	/**
	 * 校验主键ID是否存在，存在则返回，不存在则抛出异常
	 *
	 * @param id 主键ID
	 * @return Object
	 */
	Object checkAndReturn(Long id);

}
