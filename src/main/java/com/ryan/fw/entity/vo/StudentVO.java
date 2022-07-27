package com.ryan.fw.entity.vo;

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
    private Integer isExist;
    private String remark;
}
