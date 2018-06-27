package com.onarinskyi.config;

import com.onarinskyi.environment.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:api.properties")
public class ApiConfig {

    @Bean
    public Api api(@Value("${api.url}") String baseUrl) {
        return new Api(baseUrl);
    }
}
