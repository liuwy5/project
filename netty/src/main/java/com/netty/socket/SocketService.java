package com.netty.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Log4j2
public class SocketService {
    // 用来存已连接的客户端
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    //推送的事件
    public static final String PUSH_EVENT = "push_event";

    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * Spring IoC容器创建之后，在加载SocketIOServiceImpl Bean之后启动
     * @throws Exception
     */
    @PostConstruct
    private void autoStartup() throws Exception {
        start();
    }

    /**
     * Spring IoC容器在销毁SocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
     * @throws Exception
     */
    @PreDestroy
    private void autoStop() throws Exception  {
        stop();
    }

    public void start() {
        // 监听客户端连接
        socketIOServer.addConnectListener(client -> {
            String loginUserNum = getParamsByClient(client);
            if (loginUserNum != null) {
                clientMap.put(loginUserNum, client);
            }
            String channel = getRoomByClient(client);
            if (StringUtils.isNotBlank(channel)) {
                client.joinRoom(channel);
            }

            log.info("{} connect, channel: {}", loginUserNum, channel);

//            client.sendEvent("broadcast", "hello");
            // broadcast to channel "channel_1"
            socketIOServer.getRoomOperations(channel).sendEvent("broadcast", "hello");
        });

        // 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            String loginUserNum = getParamsByClient(client);
            if (loginUserNum != null) {
                clientMap.remove(loginUserNum);
                client.disconnect();
            }
            log.info(loginUserNum + " disconnect");
        });


        // 处理自定义的事件，与连接监听类似
        socketIOServer.addEventListener(PUSH_EVENT, PushMessage.class, (client, data, ackSender) -> {
            log.info("server get " + PUSH_EVENT + " message: " + data + ", ackSender: " + ackSender);
            // TODO do something
        });
        socketIOServer.start();
    }


    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    public void pushMessageToUser(PushMessage pushMessage) {
        String loginUserNum = pushMessage.getLoginUserNum();
        if (StringUtils.isNotBlank(loginUserNum)) {
            SocketIOClient client = clientMap.get(loginUserNum);
            if (client != null)
                client.sendEvent(PUSH_EVENT, pushMessage);
        }
    }

    /**
     * 此方法为获取client连接中的参数，可根据需求更改
     * @param client
     * @return
     */
    private String getParamsByClient(SocketIOClient client) {
        // 从请求的连接中拿出参数（这里的loginUserNum必须是唯一标识）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> list = params.get("loginUserNum");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    private String getRoomByClient(SocketIOClient client) {
        // 从请求的连接中拿出参数（这里的loginUserNum必须是唯一标识）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> list = params.get("room");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}

class PushMessage {
    private String loginUserNum;

    public String getLoginUserNum() {
        return loginUserNum;
    }

    public void setLoginUserNum(String loginUserNum) {
        this.loginUserNum = loginUserNum;
    }
}
