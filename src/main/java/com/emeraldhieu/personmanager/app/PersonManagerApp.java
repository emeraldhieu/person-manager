package com.emeraldhieu.personmanager.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableRetry
@EnableScheduling
public class PersonManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(PersonManagerApp.class, args);
    }

}
