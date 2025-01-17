package com.knowly.rest_api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all paths
                .allowedOrigins("https://knowly-app-front-next-88aa-4lk4r4fd3-bakops-projects.vercel.app")  // Replace with the correct frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*")  // Allow all headers (or you can specify specific headers)
                .allowCredentials(true);  // Allow credentials if needed (like cookies)
    }
}
