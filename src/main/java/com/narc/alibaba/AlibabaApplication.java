package com.narc.alibaba;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : Narcssus
 * @date : 2019/11/17 17:14
 */
@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class AlibabaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaApplication.class, args);
    }

}
