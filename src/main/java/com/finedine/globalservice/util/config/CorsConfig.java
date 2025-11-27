package com.finedine.globalservice.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Allow requests from React development server
        corsConfiguration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "http://127.0.0.1:3000"
        ));

        // Allow all HTTP methods
        corsConfiguration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
        ));

        // Allow all headers
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        // Allow credentials (cookies, authorization headers)
        corsConfiguration.setAllowCredentials(true);

        // How long the browser should cache preflight requests (1 hour)
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}