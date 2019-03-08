package com.iggi.example.common.config;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.ListCompareAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iggi.example.common.util.CommonUtils;

@Configuration
public class JaversConfig {
    @Bean
    public Javers buildJavers() {

        return JaversBuilder.javers()
                            .withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
                            .registerValue(String.class, CommonUtils::equalsIgnoreCase)
                            .build();
    }
}
