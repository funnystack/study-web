package com.funny.study.springboot.config;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/** 
 * @author  fangli
 * @date 创建时间：2017年7月11日 下午5:18:14 
 */

@Configuration
public class MyBatisConfig {

	 private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);
	
	 @Bean
	 public PageHelper pageHelper() {
        logger.info("注册MyBatis分页插件PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
	 }

}
