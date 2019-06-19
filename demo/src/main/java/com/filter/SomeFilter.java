package com.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

//public class SomeFilter implements Filter {
public class SomeFilter {

    private static final Logger logger = LoggerFactory.getLogger(SomeFilter.class);

//    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("---------some filter init");
    }

//    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("------------some filter");
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("------------some filter1");
    }

//    @Override
    public void destroy() {
        logger.info("---------some filter destroy");
    }
}
