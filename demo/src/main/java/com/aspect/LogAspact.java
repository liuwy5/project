package com.aspect;


import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;


@Aspect
@Component
public class LogAspact {
    private static final Logger logger = LoggerFactory.getLogger(LogAspact.class);

    @Around("execution(* com..*(..)) || execution(* com.restcontroller.*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        Object result = null;
        if (sra != null) {
            HttpServletRequest request = sra.getRequest();

            String requestPath = request.getRequestURI();
            String method = request.getMethod();

            Object[] parm = pjp.getArgs();//请求参数

            StringBuilder stringBuilder = new StringBuilder("path: " + requestPath);
            try {
                if (parm.length > 0) {
                    if ("GET".equals(method)) {
                        stringBuilder.append("&" + request.getQueryString());
                    } else {
                        if (!requestPath.contains("uploadFile") && !requestPath.contains("importExcel") &&
                                !requestPath.contains("importDevice")) {
                            String jsonParam = JSON.toJSONString(parm);
                            if (jsonParam.contains("{")) {
                                stringBuilder.append(", params: " + jsonParam);
                            } else {
                                Signature signature = pjp.getSignature();
                                MethodSignature methodSignature = (MethodSignature) signature;
                                // 通过这获取到方法的所有参数名称的字符串数组
                                String[] parameterNames = methodSignature.getParameterNames();
                                try {
                                    Map<String, Object> paramMap = new LinkedHashMap<>();
                                    for (int i = 0; i < parm.length; i++) {
                                        if (parm[i] != null) {
                                            paramMap.put(parameterNames[i], parm[i]);
                                        }
                                    }
                                    stringBuilder.append(", params: " + JSON.toJSONString(paramMap));
                                } catch (Exception e) {
                                    stringBuilder.append(", params: " + jsonParam);
                                    logger.error("params: " + jsonParam, e);
                                }
                            }
//                      logger.info("requestPath: " + requestPath + ", params: " + JSON.toJSONString(parm));
                        }
//                    stringBuilder.append(", params: " + JSON.toJSONString(parm));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            result = pjp.proceed();
            stringBuilder.append(", result: " + result);
            logger.info(stringBuilder.toString());
        }
        return result;
    }
}
