package com.medicea.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by Arjun on 6/11/2017.
 */
@Component
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com.medicea")
public class StartService {
    public static void main(String[] args) {
        SpringApplication.run(StartService.class, args);
    }
}
