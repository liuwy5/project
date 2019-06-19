package com.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginFilter {
//public class LoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    
//    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("---------login filter init");
    }

//    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        logger.info("----------filter");
        logger.info(request.getRequestURI());
        Map<String, String[]> paramMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            System.out.print(entry.getKey() + ": [");
            for (String item : entry.getValue()) {
                System.out.print(item + ", ");
            }
            System.out.print("], ");
        }
        System.out.println("");

        filterChain.doFilter(request, response);
        logger.info("----------filter1");
    }

//    @Override
    public void destroy() {
        logger.info("---------login filter destroy");
    }
}
