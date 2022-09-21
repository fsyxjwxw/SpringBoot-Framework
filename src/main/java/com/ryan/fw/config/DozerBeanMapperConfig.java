package com.ryan.fw.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

/**
 * @author Ryan
 * @date 2022/9/21 15:46
 * @description
 */
public class DozerBeanMapperConfig {

    private DozerBeanMapperConfig() {
    }

    public static Mapper getInstance() {
        return Holder.build;
    }

    private static class Holder {
        private static final Mapper build = DozerBeanMapperBuilder.buildDefault();
    }


}
