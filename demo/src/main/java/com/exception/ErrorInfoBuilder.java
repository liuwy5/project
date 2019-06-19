package com.exception;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

/**
 * ErrorBuilder之所以使用@Order注解和实现HandlerExceptionResolver接口是为了获取错误/异常
 * 通常情况下@ExceptionHandler并不需要这么做，因为在映射方法注入Throwable就可以获得错误/异常，
 * 这是主要是为了ErrorController根据Request对象快速获取错误/异常
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class ErrorInfoBuilder implements HandlerExceptionResolver, Ordered {

    /**
     * 错误KEY
     */
    private final static String ERROR_NAME = "error";

    private ErrorProperties errorProperties;

    public ErrorInfoBuilder(ServerProperties serverProperties) {
        this.errorProperties = serverProperties.getError();
    }

    public ErrorInfo getErrorInfo(HttpServletRequest request) {
        return getErrorInfo(request, getError(request));
    }

    public ErrorInfo getErrorInfo(HttpServletRequest request, Throwable error) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setUrl(request.getRequestURI());
        errorInfo.setError(error.toString());
        errorInfo.setStatusCode(getHttpStatus(request).value());
        errorInfo.setReasonPhrase(getHttpStatus(request).getReasonPhrase());
        errorInfo.setStackTrace(getStackTraceInfo(error, isIncludeStackTrade(request)));
        errorInfo.setTime(LocalDateTime.now().toString());
        return errorInfo;
    }

    public Throwable getError(HttpServletRequest request) {
        //根据HandlerExceptionResolver接口方法来获取错误
        Throwable error = (Throwable) request.getAttribute(ERROR_NAME);

        if (error == null) {
            error = (Throwable) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
        }

        //当获取错误非空,取出RootCause
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
        } else {
            String message = (String) request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
            if (StringUtils.isEmpty(message)) {
                HttpStatus httpStatus = getHttpStatus(request);
                message = "Unknown Exception But " + httpStatus.value() + " " + httpStatus.getReasonPhrase();
            }

            error = new Exception(message);
        }

        return error;
    }

    /**
     * 获取通信状态
     */
    public HttpStatus getHttpStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
        try {
            return statusCode != null ? HttpStatus.valueOf(statusCode) : HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 获取堆栈轨迹(StackTrace)
     */
    public String getStackTraceInfo(Throwable error, boolean flag) {
//        if (!flag) {
//            return "omitted";
//        }
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        return stackTrace.toString();
    }

    public boolean isIncludeStackTrade(HttpServletRequest request) {
        // 读取配置
        ErrorProperties.IncludeStacktrace includeStacktrace = errorProperties.getIncludeStacktrace();

        if (includeStacktrace == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }

        // 若请求参数含有trace
        if (includeStacktrace == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            String parameter = request.getParameter("trace");
            return parameter != null && !"false".equalsIgnoreCase(parameter);
        }

        return false;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        httpServletRequest.setAttribute(ERROR_NAME, e);
        return null;
    }

    public ErrorProperties getErrorProperties() {
        return errorProperties;
    }

    public void setErrorProperties(ErrorProperties errorProperties) {
        this.errorProperties = errorProperties;
    }
}
