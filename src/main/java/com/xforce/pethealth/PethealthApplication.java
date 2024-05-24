package com.xforce.pethealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PethealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PethealthApplication.class, args);
    }

}
