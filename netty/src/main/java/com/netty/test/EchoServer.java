package com.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(group).
                    channel(NioServerSocketChannel.class).  // 指定使用NIO的传输channel
                    localAddress(new InetSocketAddress(port)). // 设置socket地址使用所选的端口
                    childHandler(new ChannelInitializer<SocketChannel>() { // 添加EchoServerHandler到channel的ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind().sync(); // 绑定服务器 sync等待服务器关闭
            System.out.println(EchoServer.class.getName() + " started and listen on " + channelFuture.channel().localAddress());

            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new EchoServer(9031).start();
    }
}
