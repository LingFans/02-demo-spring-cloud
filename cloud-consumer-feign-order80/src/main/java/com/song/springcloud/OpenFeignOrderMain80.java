package com.song.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableEurekaClient
// 启动Feign
@EnableFeignClients
public class OpenFeignOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignOrderMain80.class, args);
    }
}
