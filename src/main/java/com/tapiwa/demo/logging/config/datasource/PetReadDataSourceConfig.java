package com.tapiwa.demo.logging.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.tapiwa.demo.logging.repositories.read",
        entityManagerFactoryRef = "petReadEntityManager",
        transactionManagerRef = "petReadTransactionManager")
public class PetReadDataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix="spring.datasource-replica")
    public DataSource petReadDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5433/postgres")
                .username("user")
                .password("password")
                .driverClassName("org.postgresql.Driver")
                .build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean petReadEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(petReadDataSource());
        em.setPackagesToScan("com.tapiwa.demo.logging.models");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto", "update"));
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        em.setJpaPropertyMap(properties);

        return em;
    }
    @Bean
    public PlatformTransactionManager petReadTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(petReadEntityManager().getObject());
        return transactionManager;
    }
}
