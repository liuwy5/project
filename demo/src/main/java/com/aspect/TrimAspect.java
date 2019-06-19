package com.aspect;

import com.annotation.TrimObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Aspect
public class TrimAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrimAspect.class);

    @Around("@annotation(com.annotation.Trim)")
    public Object objectTrim(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            Class clazz = arg.getClass();
            if (clazz.isAnnotationPresent(TrimObject.class)) {
                LOGGER.info("{}执行trim", clazz);
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.get(arg) instanceof String) {
                        field.set(arg, ((String) field.get(arg)).trim());
                    }
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
