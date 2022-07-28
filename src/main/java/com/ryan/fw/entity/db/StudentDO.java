package com.ryan.fw.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Ryan
 * @date 2022/7/27 10:57
 * @description
 */
@TableName("fw_student")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class StudentDO extends BaseDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Integer isExist;
    private String remark;
}
