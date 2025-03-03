package com.itbl.monitor.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.itbl.monitor.repository.vtb", entityManagerFactoryRef = "vtbEntityManagerFactory", transactionManagerRef = "vtbTransactionManager")
public class VtbDataSourceConfig {

    @Primary
    @Bean(name = "vtbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.vtb")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "vtbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("vtbDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.itbl.monitor.model.vtb")
                .persistenceUnit("vtb")
                .build();
    }

    @Primary
    @Bean(name = "vtbTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("vtbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
