package com.xforce.pethealth.shared.infraestructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/*")
                        .allowedOrigins("http://localhost:4200", "https://backend-production-6ed3.up.railway.app", "https://pet-health.netlify.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("")
                        .allowCredentials(true);
            }
        };
    }
}