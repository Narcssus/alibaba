package com.narc.alibaba;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author : Narcssus
 * @date : 2019/11/17 17:14
 */
@SpringBootApplication
@Slf4j
public class SwaggerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }

}
