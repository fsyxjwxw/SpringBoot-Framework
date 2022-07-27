package com.ryan.fw.entity.co;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ryan
 * @date 2022/7/27 17:09
 * @description 返回结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public static Result success(String message, Object data) {
        return Result.builder().code(200).message(message).data(data).build();
    }

    public static Result err(String message){
        return Result.builder().code(201).message(message).data("").build();
    }

}
