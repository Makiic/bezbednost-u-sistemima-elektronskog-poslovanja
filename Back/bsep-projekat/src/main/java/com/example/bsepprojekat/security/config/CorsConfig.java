package com.example.bsepprojekat.security.config;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:4200"));

        config.setAllowedMethods(List.of("GET", "HEAD", "PUT", "PATCH", "POST", "DELETE", "OPTIONS", "CONNECT", "TRACE"));

        config.setAllowedHeaders(List.of(
                "Content-Type", "Authorization", "X-Content-Type-Options",
                "Accept", "X-Requested-With", "Origin",
                "Access-Control-Request-Method", "Access-Control-Request-Headers", "Access-Control-Allow-Origin"
        ));

        config.setAllowCredentials(true);

        config.addExposedHeader("Access-Control-Allow-Private-Network");

        config.setMaxAge(7200L);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
 */

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Authorization")
                .allowCredentials(true);
    }
}
