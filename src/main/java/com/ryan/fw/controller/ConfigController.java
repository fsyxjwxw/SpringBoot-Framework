package com.ryan.fw.controller;

import com.ryan.fw.config.JsonConfig;
import com.ryan.fw.entity.co.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ryan
 * @date 2022/8/6 17:38
 * @description 配置数据控制层
 */
@Api(tags = "【配置数据】")
@RestController
public class ConfigController {

    @ApiOperation("获取装备信息")
    @GetMapping("/getEquipment")
    public Result getEquipment(){
        return Result.success("查询成功", JsonConfig.getEquipmentMap());
    }

}
