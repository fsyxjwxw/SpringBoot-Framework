package com.ryan.fw.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ryan
 * @date 2022/8/1 20:51
 * @description 分页查询返回结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentPageVO {
    private List<StudentVO> data;
    private Long count;
}
