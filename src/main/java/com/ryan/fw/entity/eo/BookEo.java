package com.ryan.fw.entity.eo;

import cn.easyes.annotation.IndexName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ryan
 * @date 2022/8/9 9:45
 * @description
 */
@Data

public class BookEo {
    private Long id;
    private String name;
    private BigDecimal price;
    private String author;
    private String type;
    private String description;
}
