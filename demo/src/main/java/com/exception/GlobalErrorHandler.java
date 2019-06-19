package com.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 * 基于@ExceptionHandler
 */
//@ControllerAdvice
public class GlobalErrorHandler {

    @Autowired
    ErrorInfoBuilder errorInfoBuilder;

//    @ExceptionHandler
    @ResponseBody
    public ErrorInfo errorHandle(HttpServletRequest request, Exception e) {
        ErrorInfo errorInfo = errorInfoBuilder.getErrorInfo(request);
        return errorInfo;
    }
}
