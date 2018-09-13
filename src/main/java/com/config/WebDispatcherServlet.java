package com.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
/**
 * Main configuration of Spring Context.
 * Registration Configurations {@link SpringConfig},{@link WebConfig},{@link SpringSecurityConfig},{@link PropertiesConfig}.
 * @author Serhii Zhuravlov
 */
public class WebDispatcherServlet implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext){
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class,WebConfig.class,SpringSecurityConfig.class,PropertiesConfig.class);
        context.setServletContext(servletContext);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));

        /* Manages the lifecycle of the root application context */
        servletContext.addListener(new ContextLoaderListener(context));

        /* Spring Security Registration */

        DelegatingFilterProxy filter  = new DelegatingFilterProxy("springSecurityFilterChain");

        servletContext.addFilter("springSecurityFilterChain",filter).addMappingForUrlPatterns(null,false,"/*");

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
