package com.iggi.example.lov.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.DefaultEntityMapper;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.iggi.example.common.config.PrimaryDataSourceConfig;
import com.iggi.example.common.config.QueryDslJpaConfig;

@Configuration
@Import({ QueryDslJpaConfig.class, PrimaryDataSourceConfig.class })
@ComponentScan(basePackages = { "com.iggi.example.lov", "com.iggi.platform.logging" })
@EntityScan("com.iggi.example.lov")
@EnableAutoConfiguration
@EnableElasticsearchRepositories(basePackages = "com.iggi.example.lov.data.elasticsearch.repository")
public class AppConfig {
    @Bean
    public DefaultEntityMapper getDefaultEntityMapper() {
        return new DefaultEntityMapper(new SimpleElasticsearchMappingContext());
    }
}
