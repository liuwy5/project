package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AutoConfigureOrder
public class ZkApplication implements Runnable {
    public static void main(String[] args) {
        SpringApplication.run(ZkApplication.class, args);
    }

    @Override
    public void run() {

    }
}
