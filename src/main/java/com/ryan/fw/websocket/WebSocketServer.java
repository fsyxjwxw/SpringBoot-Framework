package com.ryan.fw.websocket;

import com.alibaba.fastjson.JSON;
import com.ryan.fw.entity.vo.WebSocketVo;
import com.ryan.fw.utils.WebSocketUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Ryan
 * @date 2022/8/1 17:33
 * @note WebSocket服务端
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{id}")
public class WebSocketServer {

    private static Map<String, WebSocketServer> map = new HashMap<>(16);

    private Session session;

    /**
     * WebSocket连接
     *
     * @param session 连接session
     * @param id      连接id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.session = session;

        String ip = WebSocketUtils.getRemoteAddress(session);
        if (Objects.nonNull(ip)) {
            id = ip + "-" + id;
        }
        map.put(id, this);

        try {
            sendMessage(id, WebSocketVo.builder().flag("WebSocketConnect").status(true).message("WebSocket连接注册成功").data("WebSocket连接注册成功").build());
        } catch (IOException e) {
            log.error("【WebSocket】推送至" + id + "消息失败");
            e.printStackTrace();
        }

        log.info("【WebSocket】页面: " + id + " WebSocket注册连接成功");
    }

    /**
     * WebSocket连接关闭
     *
     * @param session 连接session
     * @param id      连接id
     */
    @OnClose
    public void onClose(Session session, @PathParam("id") String id) {
        String ip = WebSocketUtils.getRemoteAddress(session);
        if (Objects.nonNull(ip)) {
            id = ip + "-" + id;
        }
        map.remove(id);
        log.info("【WebSocket】页面 " + id + " 断开连接");
        log.info("【WebSocket】当前断开IP" + ip);
        log.info("【WebSocket】当前连接集合:" + map);
        WebSocketVo vo = WebSocketVo.builder().flag("WebSocketDisconnect").status(true).message(id + "页面断开连接").data(id).type("setFlag").build();
        sendAllSession(vo, ip);
    }

    /**
     * 发送消息
     *
     * @param id 连接id
     * @param vo 消息内容
     * @return
     * @throws IOException
     */
    public static Boolean sendMessage(String id, WebSocketVo vo) throws IOException {
        WebSocketServer webSocketServer = map.get(id);
        if (webSocketServer.session.isOpen()) {
            try {
                webSocketServer.session.getBasicRemote().sendText(JSON.toJSONString(vo));
            } catch (IllegalStateException e) {
                return false;
            }
            return true;
        } else {
            log.error("【WebSocket】接收方连接已关闭");
            return false;
        }
    }

    /**
     * 广播发送特定IP消息
     *
     * @param vo 消息内容
     * @param ip 指定IP地址
     * @return
     */
    public synchronized static Boolean sendAllSession(WebSocketVo vo, String ip) {
        for (Map.Entry<String, WebSocketServer> entry : map.entrySet()) {
            WebSocketServer ws = entry.getValue();
            String key = entry.getKey();
            if (key.indexOf(ip) > -1) {
                try {
                    log.info("【WebSocket】发送" + entry.getKey() + "页面" + vo.getData() + "断开连接");
                    if (ws.session.isOpen()) {
                        ws.session.getBasicRemote().sendText(JSON.toJSONString(vo));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证当前连接是否存在
     *
     * @param id 连接id
     * @return Boolean
     */
    public synchronized static Boolean vertifyId(String id) {
        return Objects.isNull(map.get(id)) ? false : true;
    }

}
