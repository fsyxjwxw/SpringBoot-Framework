package com.ryan.fw.instance;

import lombok.NoArgsConstructor;
import org.dozer.DozerBeanMapper;

/**
 * @author Ryan
 * @date 2022/7/27 18:10
 * @description 转换工具实例
 */
@NoArgsConstructor
public class DozerBean {

    public static DozerBeanMapper getInstance(){
        return DozerBeanHolder.DOZER_BEAN_MAPPER;
    }

    private static class DozerBeanHolder{
        private static final DozerBeanMapper DOZER_BEAN_MAPPER = new DozerBeanMapper();
    }

}
