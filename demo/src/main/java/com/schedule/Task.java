package com.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

//    @Async
//    @Scheduled(cron = "0/1 * * * * *")
    public void task() {
        LOGGER.info("每一秒执行一次: {}", LocalDateTime.now());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
