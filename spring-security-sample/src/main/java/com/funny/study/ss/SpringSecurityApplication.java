package com.funny.study.ss;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.funny.study.ss.mapper")
@EnableTransactionManagement
public class SpringSecurityApplication {
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}
}
