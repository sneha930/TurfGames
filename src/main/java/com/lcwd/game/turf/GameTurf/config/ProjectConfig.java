package com.lcwd.game.turf.GameTurf.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProjectConfig {

    @Bean
    public ModelMapper mapper() {
        return  new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Apply to all endpoints
                        .allowedOrigins("http://localhost:3000")  // Your React app
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Must include OPTIONS
                        .allowedHeaders("*")
                        .allowCredentials(true);  // If using cookies or auth
            }
        };
    }
}