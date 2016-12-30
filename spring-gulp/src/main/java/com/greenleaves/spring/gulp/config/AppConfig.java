package com.greenleaves.spring.gulp.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by sgdn001 on 7/15/2016.
 */

@Configuration
@ComponentScan(basePackages = { "com.greenleaves.spring.gulp"})
@EnableAspectJAutoProxy
//@EnableJpaRepositories
public class AppConfig {

}
