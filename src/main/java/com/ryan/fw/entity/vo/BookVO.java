package com.ryan.fw.entity.vo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Ryan
 * @date 2022/8/8 17:08
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookVO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String author;
    private String type;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String description;
}
