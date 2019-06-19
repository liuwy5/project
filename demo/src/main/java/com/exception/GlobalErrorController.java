package com.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 * 基于ErrorController接口
 */
@Controller
@RequestMapping("${server.error.path:/error}")
public class GlobalErrorController implements ErrorController {

    @Autowired
    private ErrorInfoBuilder errorInfoBuilder;

    private final static String DEFAULT_ERROR_VIEW = "error";

    /**
     * 情况1：若预期返回类型为text/html,则返回错误信息页(View)
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request) {
        return new ModelAndView(DEFAULT_ERROR_VIEW, "errorInfo", errorInfoBuilder.getErrorInfo(request));
    }

    /**
     * 情况2：其它预期类型 则返回详细的错误信息(JSON).
     */
    @RequestMapping
    @ResponseBody
    public ErrorInfo error(HttpServletRequest request) {
        return errorInfoBuilder.getErrorInfo(request);
    }

    @Override
    public String getErrorPath() {
        return errorInfoBuilder.getErrorProperties().getPath();
    }
}
