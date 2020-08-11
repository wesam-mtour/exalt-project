package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
 * The @SpringBootApplication annotation is equivalent to using:
   @Configuration , @EnableAutoConfiguration and @ComponentScan

 * @Configuration
annotation indicates that a class declares one or more @Bean methods "extra Beans"

 * @EnableAutoConfiguration
automatically configures classes present on the classpath

 * @ComponentScan
annotation to tell Spring the packages to scan for annotated components
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = "com.exalt.sparepartsmanagement.mapper")
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
