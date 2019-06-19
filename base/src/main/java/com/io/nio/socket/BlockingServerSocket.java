package com.io.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockingServerSocket {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 绑定连接
            serverSocketChannel.bind(new InetSocketAddress(9898));
            // 获取客户端连接的通道
            SocketChannel socketChannel = serverSocketChannel.accept();

            FileChannel fileChannel = FileChannel.open(Paths.get("file/target"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);


            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(byteBuffer) != -1) {
                // 将图片保存到本地
                byteBuffer.flip();

                fileChannel.write(byteBuffer);
                byteBuffer.clear();
            }

            String message = "服务端保存完成";
            System.out.println(message);

            // 服务端发送数据
            byteBuffer.put(message.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);

            socketChannel.close();
            fileChannel.close();
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
