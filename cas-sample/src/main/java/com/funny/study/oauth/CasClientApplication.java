package com.funny.study.oauth;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EnableCasClient
public class CasClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasClientApplication.class, args);
    }
}
