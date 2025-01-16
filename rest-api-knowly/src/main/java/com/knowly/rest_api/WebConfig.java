package com.knowly.rest_api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Applique CORS à tous les endpoints de votre API
                .allowedOrigins("http://localhost:3000") // Remplacez par l'URL de votre app Next.js
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Autorise ces méthodes HTTP
                .allowedHeaders("*") // Autorise tous les headers
                .allowCredentials(true); // Permet l'envoi de cookies ou d'informations d'authentification
    }
}
