package com.iggi.example.common.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EntityScan("com.iggi.example")
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
                       transactionManagerRef = "transactionManager",
                       basePackages = {"com.iggi.example" })
public class PrimaryDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariDataSource dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder()
                                     .type(HikariDataSource.class)
                                     .build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                      .packages("com.iggi.example")
                      .persistenceUnit("masterdatamanagement")
                      .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
