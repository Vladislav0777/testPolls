package com.example.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Getter
@Setter
@Configuration
@ComponentScan("com.example.demo")
@PropertySource("classpath:application.properties")
@EnableWebMvc
@EnableTransactionManagement
@RequiredArgsConstructor
public class Config {

    private final Environment environment;

    private static final String JDBC_DRIVER = "spring.datasource.driver-class-name";
    private static final String JDBC_URL = "spring.datasource.url";
    private static final String JDBC_USER = "spring.datasource.username";
    private static final String JDBC_PASSWORD = "spring.datasource.password";
    private static final String HIBERNATE_HBM2DDL_AUTO = "spring.jpa.hibernate.ddl-auto";
    private static final String HIBERNATE_SHOW_SQL = "spring.jpa.show-sql";
    private static final String HIBERNATE_DIALECT = "spring.jpa.properties.hibernate.dialect";
    private static final String HIBERNATE_CHAR_SET = "spring.jpa.properties.hibernate.charSet";
    private static final String HIBERNATE_CHARACTER_ENCODING = "spring.jpa.properties.hibernate.characterEncoding";
    private static final String HIBERNATE_USE_UNICODE = "spring.jpa.properties.hibernate.useUnicode";


    @Bean
    @DependsOn("flyway")
    public DataSource dataSource() {
        final ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(environment.getProperty(JDBC_DRIVER));
            dataSource.setJdbcUrl(environment.getProperty(JDBC_URL));
            dataSource.setUser(environment.getProperty(JDBC_USER));
            dataSource.setPassword(environment.getProperty(JDBC_PASSWORD));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.example.demo");

        Properties hibernateProps = new Properties();
        hibernateProps.setProperty(HIBERNATE_DIALECT, environment.getProperty(HIBERNATE_DIALECT));
        hibernateProps.setProperty(HIBERNATE_HBM2DDL_AUTO, environment.getProperty(HIBERNATE_HBM2DDL_AUTO));
        hibernateProps.setProperty(HIBERNATE_SHOW_SQL, environment.getProperty(HIBERNATE_SHOW_SQL));
        hibernateProps.setProperty(HIBERNATE_CHAR_SET, environment.getProperty(HIBERNATE_CHAR_SET));
        hibernateProps.setProperty(HIBERNATE_CHARACTER_ENCODING, environment.getProperty(HIBERNATE_CHARACTER_ENCODING));
        hibernateProps.setProperty(HIBERNATE_USE_UNICODE, environment.getProperty(HIBERNATE_USE_UNICODE));
        sessionFactory.setHibernateProperties(hibernateProps);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
