package com.ryan.fw.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Ryan
 * @date 2022/8/2 9:43
 * @description
 */
@ApiModel("新增学生信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    @NotNull(message = "姓名不得为空")
    @ApiModelProperty(name = "name", value = "姓名", required = true)
    private String name;
    @NotNull(message = "年龄不得为空")
    @ApiModelProperty(name = "age", value = "年龄", required = true)
    private Integer age;
    @NotNull(message = "性别不得为空")
    @ApiModelProperty(name = "sex", value = "性别", required = true)
    private String sex;
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
}
