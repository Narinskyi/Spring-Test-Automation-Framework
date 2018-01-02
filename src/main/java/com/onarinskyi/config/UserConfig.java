package com.onarinskyi.config;

import com.onarinskyi.environment.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public User adminUser(@Value("${admin.name}") String adminName, @Value("${admin.password}") String adminPassword) {
        return new User(adminName, adminPassword);
    }

    @Bean
    public User user(@Value("${user.name}") String userName, @Value("${user.password}") String userPassword) {
        return new User(userName, userPassword);
    }
}
