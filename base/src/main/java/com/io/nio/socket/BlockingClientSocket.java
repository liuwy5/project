package com.io.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockingClientSocket {
    public static void main(String[] args) {
        try {
            // 获取通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
            FileChannel fileChannel = FileChannel.open(Paths.get("file/source"), StandardOpenOption.READ);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 文件读入缓冲区
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();

                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            System.out.println("上传文件完成");

            socketChannel.shutdownOutput();

            // 读取服务器消息
            while (socketChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();

                System.out.println("接收到服务器信息：" + new String(byteBuffer.array(), 0, byteBuffer.limit()));
                byteBuffer.clear();
            }

            fileChannel.close();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
