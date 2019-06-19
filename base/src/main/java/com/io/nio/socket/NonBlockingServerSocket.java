package com.io.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NonBlockingServerSocket {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            // 切换非阻塞模式
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.bind(new InetSocketAddress(9899));

            System.out.println("服务端启动");

            // 选择器
            Selector selector = Selector.open();

            // 将通道注册到选择器上
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 有事件准备就绪
            while (selector.select() > 0) {
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        // 切换非阻塞模式
                        socketChannel.configureBlocking(false);
                        // 将通道注册到选择器上
                        socketChannel.register(selector, SelectionKey.OP_READ);

                        System.out.println("有客户端连接");
                    } else if (selectionKey.isReadable()) {
//                        System.out.println("------------");
                        // 获取客户端channel
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = socketChannel.read(byteBuffer)) > 0) {
                            byteBuffer.flip();
                            System.out.println(new String(byteBuffer.array(), 0, len));
                            byteBuffer.clear();
                        }
//                        socketChannel.read(byteBuffer);

//                        System.out.println(byteBuffer.position() + byteBuffer.limit());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    // 取消选择键 selectionKey
                    selectionKeyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
