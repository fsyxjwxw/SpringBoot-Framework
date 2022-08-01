package com.ryan.fw.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ryan
 * @date 2022/8/1 17:33
 * @description WebsocketVo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebSocketVo {
    private String flag;
    private Boolean status;
    private String message;
    private Object data;
    private String type;
}
