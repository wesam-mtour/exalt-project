package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
The @SpringBootApplication annotation is equivalent to using:
 @Configuration , @EnableAutoConfiguration and @ComponentScan
 */

/*
@EnableAutoConfiguration
 automatically configures classes present on the classpath
 */
@EnableAutoConfiguration
/*
@Configuration
annotation indicates that a class declares one or more @Bean methods "extra Beans"
 */
@Configuration
/*
@ComponentScan
annotation to tell Spring the packages to scan for annotated components
 */
@ComponentScan("com.exalt.sparepartsmanagement")
@MapperScan(value = "com.exalt.sparepartsmanagement.mapper")
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}

}
