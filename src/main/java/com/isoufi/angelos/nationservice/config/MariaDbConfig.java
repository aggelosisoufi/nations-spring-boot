package com.isoufi.angelos.nationservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.isoufi.angelos.nationservice.repository.mariadb",
        entityManagerFactoryRef = "mariaDbEntityManagerFactory",
        transactionManagerRef = "mariaDbTransactionManager"
)
public class MariaDbConfig {

    @Bean(name = "mariaDbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mariadb")
    public DataSource mariaDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mariaDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mariaDbEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mariaDbDataSource") DataSource dataSource) {

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "none");                // Don't touch schema
        props.put("hibernate.jdbc.batch_size", 20);                 // Enable batch inserts
        props.put("hibernate.order_inserts", true);                 // Optimize insert order
        props.put("hibernate.order_updates", true);                 // Optimize update order
        props.put("hibernate.generate_statistics", true);           // Enable Hibernate stats
        props.put("hibernate.dialect", "org.hibernate.dialect.MariaDB103Dialect"); // DB dialect

        return builder
                .dataSource(dataSource)
                .packages("com.isoufi.angelos.nationservice.entity.mariadb")
                .persistenceUnit("mariaDbPU")
                .properties(props)
                .build();
    }

    @Bean(name = "mariaDbTransactionManager")
    public PlatformTransactionManager mariaDbTransactionManager(
            @Qualifier("mariaDbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
