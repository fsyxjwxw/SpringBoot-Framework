package com.ryan.fw.entity.db;

import cn.easyes.annotation.IndexId;
import cn.easyes.annotation.IndexName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Ryan
 * @date 2022/8/8 9:36
 * @description 书-实体
 */
@TableName("fw_book")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@IndexName("fw_book")
public class BookDO extends BaseDO {
    @TableId(type = IdType.AUTO)
    @IndexId(type = cn.easyes.common.enums.IdType.CUSTOMIZE)
    private Long id;
    private String name;
    private BigDecimal price;
    private String author;
    private String type;
    private String description;
}
