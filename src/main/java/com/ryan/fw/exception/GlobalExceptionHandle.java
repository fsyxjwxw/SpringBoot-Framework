package com.ryan.fw.exception;

import cn.hutool.core.util.StrUtil;
import com.ryan.fw.entity.co.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Ryan
 * @date 2022/7/27 17:30
 * @description 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {
    /**
     * 捕获RuntimeException并返回前端结果
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handle(RuntimeException e) {
        String message = e.getMessage();
        if (StrUtil.isBlank(message)) {
            message = "服务异常";
            e.printStackTrace();
        } else {
            log.error("【运行异常】" + message);
        }
        return Result.err(message);
    }
}
