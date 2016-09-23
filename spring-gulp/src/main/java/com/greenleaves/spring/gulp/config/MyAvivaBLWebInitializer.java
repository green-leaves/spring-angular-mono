package com.greenleaves.spring.gulp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by sgdn001 on 7/15/2016.
 */
public class MyAvivaBLWebInitializer implements WebApplicationInitializer {
    @Autowired
    Environment env;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // Add spring security filters to servletContext
        FilterRegistration.Dynamic securityFilterChain = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        securityFilterChain.addMappingForUrlPatterns(null, true, "/*");

        ClassPathResource resource = new ClassPathResource( "application.properties" );
        Properties properties = new Properties();
        try {
            InputStream inputStream = resource.getInputStream();
            properties.load(inputStream);
            String springProfile = (String) properties.get("spring.profile");
            servletContext.setInitParameter("spring.profiles.active", springProfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("springGulp", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
    }
}
