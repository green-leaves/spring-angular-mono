package com.greenleaves.spring.gulp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by sgdn001 on 7/15/2016.
 */

@Configuration
@ComponentScan(basePackages = { "com.greenleaves.spring.gulp"})
@EnableAspectJAutoProxy
public class AppConfig {

}
