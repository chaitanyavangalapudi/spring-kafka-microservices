package com.iggi.example.common.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QueryDslJpaConfig {
    @Bean
    @Primary
    public JPAQueryFactory getJpaQueryFactory(@Qualifier("entityManagerFactory")
                                     EntityManagerFactory entityManagerFactory) {
        return new JPAQueryFactory(entityManagerFactory.createEntityManager());
    }
}
