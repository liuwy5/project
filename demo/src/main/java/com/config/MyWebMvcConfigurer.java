package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Value("${virtualPath}")
    private String virtualPath;

    @Value("${uploadDir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
         * 这是图片的物理路径  "file:/+本地图片的地址"
         */
        registry.addResourceHandler(virtualPath + "**").addResourceLocations("file:" + uploadDir);
    }
}
