package com.config;

import com.filter.LoginFilter;
import com.filter.SomeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
//    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.addUrlPatterns("/1/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean filterSome() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new SomeFilter());
        filterRegistrationBean.addUrlPatterns("/1/*");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
}
