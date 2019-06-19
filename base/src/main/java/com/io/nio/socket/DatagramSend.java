package com.io.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

public class DatagramSend {
    public static void main(String[] args) {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                byteBuffer.put((new Date() + "\n" + scanner.next()).getBytes());
                byteBuffer.flip();
                datagramChannel.send(byteBuffer, new InetSocketAddress("127.0.0.1", 9897));

                byteBuffer.clear();
            }

            datagramChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
