package com.jee.yougetnicecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class YougetnicecarApplication extends SpringBootServletInitializer {


    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(YougetnicecarApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(YougetnicecarApplication.class, args);

    }

}
