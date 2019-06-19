package com.concurrency.basic;

import com.date.DateUtil;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) {
        System.out.println(DateUtil.now());
//        delayPeriodTest();
        delayFixedTest();
    }

    public static void delayTest() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("fdfd: " + DateUtil.now());
            }
        }, 2000);
    }

    public static void delayPeriodTest() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jobBody();
            }
        }, 1000, 2000);
    }

    public static void delayFixedTest() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                jobBody();
            }
        }, 1000, 2000);
    }

    private static void jobBody() {
        String startTime = DateUtil.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("startTime: " + startTime + ", endTime: " + DateUtil.now());
    }
}

