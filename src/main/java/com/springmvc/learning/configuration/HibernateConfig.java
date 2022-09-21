package com.springmvc.learning.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static com.springmvc.learning.constants.HibernateSettingsConstants.HIBERNATE_DIALECT;
import static com.springmvc.learning.constants.HibernateSettingsConstants.SHOW_SQL;
import static com.springmvc.learning.constants.HibernateSettingsConstants.HBM2DDL_AUTO;
import static com.springmvc.learning.constants.HibernateSettingsConstants.VALIDATION_MODE;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(value = {"com.springmvc.learning.service",
        "com.springmvc.learning.dao.implementation",
        "com.springmvc.learning.dao.service"})
public class HibernateConfig {
    @Autowired
    public Environment environment;

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(environment.getProperty("SPRING.DATASOURCE.USER"));
        dataSource.setPassword(environment.getProperty("PASSWORD"));
        dataSource.setDriverClassName(environment.getProperty("MYSQL_DRIVER"));
        dataSource.setUrl(environment.getProperty("URL"));
        return dataSource;
    }

    private Properties hibernateSettings() {
        Properties settings = new Properties();
        settings.put(HIBERNATE_DIALECT, environment.getProperty("MYSQL_DIALECT"));
        settings.put(SHOW_SQL, environment.getProperty("SHOW_SQL"));
        settings.put(HBM2DDL_AUTO, environment.getProperty("HIBERNATE.HBM2DDL.AUTO"));
        settings.put(VALIDATION_MODE, environment.getProperty("VALIDATION_MODE"));
        return settings;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(hibernateSettings());
        sessionFactoryBean.setPackagesToScan("com.springmvc.learning.models");
        return sessionFactoryBean;
    }

    @Bean
    public TransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
