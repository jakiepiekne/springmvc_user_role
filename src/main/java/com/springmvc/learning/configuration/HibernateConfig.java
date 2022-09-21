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
        settings.put("hibernate.dialect", environment.getProperty("MYSQL_DIALECT"));
        settings.put("hibernate.show_sql", environment.getProperty("SHOW_SQL"));
        settings.put("hibernate.hbm2ddl.auto", environment.getProperty("HIBERNATE.HBM2DDL.AUTO"));
        settings.put("javax.persistence.validation.mode", environment.getProperty("VALIDATION_MODE"));
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
