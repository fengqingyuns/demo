package com.example.demo;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.example.demo.**.dao")
public class ActivitidemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitidemoApplication.class, args);
	}

}
