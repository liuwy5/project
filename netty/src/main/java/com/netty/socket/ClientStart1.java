package com.netty.socket;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ClientStart1 {
    public static void main(String[] args) {
        String url ="http://localhost:9099";
        try{
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            options.reconnectionDelay = 1000;//失败重连的时间间隔
            options.timeout = 500;//连接超时时间(ms)
            options.query = "loginUserNum=name";
            //par1 是任意参数
            Socket socket = IO.socket(url+"?loginUserNum=12345&room=room1", options);

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("connect");
//                    socket.send("hello");
                }
            });

            //自定义事件
            socket.on("broadcast", new Emitter.Listener() {
                public void call(Object... objects) {
                    System.out.println("receive borcast data:" + objects[0].toString());
                }
            });

            socket.on("connected", new Emitter.Listener() {
                public void call(Object... objects) {
                    System.out.println("receive connected data:" + objects[0].toString());
                }
            });

            socket.on("push_event", new Emitter.Listener() {
                public void call(Object... objects) {
                    System.out.println("receive push_event data:" + objects[0].toString());
                }
            });

            socket.connect();
            //循环发送数据
//            while (true){
//                socket.emit("client_info"," 客户端在发送数据");
//                Thread.sleep(2000);
//            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
