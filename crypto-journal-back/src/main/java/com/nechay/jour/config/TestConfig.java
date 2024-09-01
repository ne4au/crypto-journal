package com.nechay.jour.config;

import com.nechay.jour.domain.UserModel;
import com.nechay.jour.service.UserService;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

@Configuration
public class TestConfig {
    @Autowired private UserService userService;
    @Autowired private SpringLiquibase springLiquibase;

    @Bean
    public Setup setup() {
        return new Setup(userService).init();
    }

    public static class Setup {
        private final UserService userService;

        public Setup(UserService userService) {
            this.userService = userService;
        }

        public Setup init() {
            userService.create(
                    new UserModel.Builder()
                        .setLogin("test_user1")
                        .setPassword("kek")
                        .setEmail("email@gmail.com")
                        .setActive(true)
                        .build())
                .block();
            return this;
        }
    }
}
