package com.aspect;

import com.annotation.ZkValue;
import com.util.ZkUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Aspect
public class ZkAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkAspect.class);

    @Autowired
    private ZkUtil zkUtil;

    @Before("@within(com.annotation.ZkConfig)")
    public void zkConfig(JoinPoint joinPoint) {
        Class clazz = joinPoint.getTarget().getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ZkValue.class)) {
                String key = field.getAnnotation(ZkValue.class).value();
                try {
                    String value = zkUtil.get(key);
                    field.set(joinPoint.getTarget(), value);
                    LOGGER.info("zk config path: {}, value: {}", key, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
