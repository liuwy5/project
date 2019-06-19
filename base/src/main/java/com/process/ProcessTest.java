package com.process;

import java.io.IOException;
import java.util.Scanner;

/**
 * 两种方法创建进程：ProcessBuilder Runtime的exec方法
 */
public class ProcessTest {

    public static void main(String[] args) {
        ProcessTest processTest = new ProcessTest();
//        processTest.processBuilderMethod();

        processTest.exceMethod();
    }

    /**
     * ProcessBuilder创建进程
     */
    public void processBuilderMethod() {
        ProcessBuilder processBuilder = new ProcessBuilder("ifconfig");
        try {
            Process process = processBuilder.start();
            execProcess(process);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runtime的exec方法创建线程
     */
    public void exceMethod() {
        try {
            Process process = Runtime.getRuntime().exec("ifconfig");
            execProcess(process);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void execProcess(Process process) {
        Scanner scanner = new Scanner(process.getInputStream());
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
