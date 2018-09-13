package com.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Configuration for Web part of Spring MVC , scanning and creating beans like : viewResolver , ResourceHandlers , multipartResolver
 * @author Serhii Zhuravlov
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.controllers")
public class WebConfig implements WebMvcConfigurer {
    /**
     * Configuration for Prefix and Suffix of jsp
     * @return {@link InternalResourceViewResolver}
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {

        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/pages/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

    /**
     * Configuration for resources like js,css to jsp tags ,
     * examples you can see in jsp files with prefix="c"
     * @param registry {@link ResourceHandlerRegistry}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * Configuration for uploading files (photos , pdf and etc)
     * @return {@link CommonsMultipartResolver}
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }


}
