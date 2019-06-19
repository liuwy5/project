package com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class MyAspect {
    private static final Logger logger = LoggerFactory.getLogger(MyAspect.class);
    
    @Before("execution(* com.restcontroller..*.*(..))")
    public void doBeforeAdvice(JoinPoint joinPoint) {
//        logger.info("aspect 前置通知");
    }

    @Around("execution(* com.restcontroller..*.*my*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("aspect url: " + request.getRequestURI());

        logger.info("aspect filter start");
        Object[] objects = proceedingJoinPoint.getArgs();
        for (Object object : objects) {
            logger.info("args: " + object);
        }

        Object result = proceedingJoinPoint.proceed(); // 获取方法返回值
        logger.info("result: " + result);
        logger.info("TimeAspect filter end");
        return result;
    }
}
