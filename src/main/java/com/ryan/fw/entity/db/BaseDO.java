package com.ryan.fw.entity.db;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Ryan
 * @date 2022/7/27 11:11
 * @description
 */
@Data
@ToString
public class BaseDO {
    /**
     * 创建时间（格式 yyyy-MM-DD HH:mm:ss）
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间（格式 yyyy-MM-DD HH:mm:ss）
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标识(0-未删除，1-已删除)
     */
    private Integer delFlag;
}
