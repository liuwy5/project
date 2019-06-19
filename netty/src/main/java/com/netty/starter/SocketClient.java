package com.netty.starter;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
public class SocketClient {
    public static void main(String[] args) {
        final Socket socket ;
        String ioSocketUrl = "http://127.0.0.1:9099";
        String msg = "a client";

        try {
            socket = IO.socket(ioSocketUrl);

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("fdfdfdfdddddddddddddddd");
                    socket.emit("client_info", msg);
                    socket.send(msg);
                    socket.disconnect();
                    socket.close();
                }
            });
            socket.connect();
            System.out.println("sendSocketMsg msg: " + msg + ", socketUrl: " + ioSocketUrl + ", result: true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
