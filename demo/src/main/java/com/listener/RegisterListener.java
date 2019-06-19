package com.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class RegisterListener implements ServletContextListener {

    private final static Logger logger = LoggerFactory.getLogger(RegisterListener.class);
    private static volatile boolean IS_REGISTRY = false;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("--------------------------context启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("--------------------------contextcontextDestroyed");
    }
}
