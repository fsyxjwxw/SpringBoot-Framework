package com.ryan.fw.entity.vo;

import com.ryan.fw.annotations.SetValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Ryan
 * @date 2022/7/27 11:59
 * @description 返回前端对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentVO {
    private Long id;
    private String name;
    private Integer age;
    private String sex;

    @ApiModelProperty(name = "isExist", value = "xxx是否存在：fase-->不存在，true-->存在")
    @SetValue(beanName = "studentService", method = "checkAndReturn", paramType = Long.class, targetFiled = "isExist")
    private Boolean isExist;

    private String remark;
}
