package com.bh.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}



/*
[Google cloud platform]
id: byeongho.ra@gmail.com
project id: springboot-webservice-343722
OAuth 2.0 Client IDs: springboot-webservice
OAuth consent screen name: allyoucanrecipe-springboot-webservice
 */