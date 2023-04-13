package com.funny.study.otp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author fangli
 */
@SpringBootApplication(scanBasePackages = "com.funny.study.otp")
@MapperScan(value={"com.funny.study.otp.dao"})
public class OTPApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OTPApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OTPApplication.class, args);
    }

}
