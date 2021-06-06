package com.example.demo.activity.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;
@Configuration
public class MybatisPlusConfig {

	 @Bean
	    public PageHelper pageHelper() {
	        System.out.println("MybatisConfiguration.pageHelper()");
	        PageHelper pageHelper =new PageHelper();
	        Properties properties =new Properties();
	        properties.setProperty("offsetAsPageNum","true");
	        properties.setProperty("rowBoundsWithCount","true");
	        properties.setProperty("reasonable","true");
	        pageHelper.setProperties(properties);
	        return pageHelper;
	    }
}
