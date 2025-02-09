package com.CleaningService.JavaProject.config;

import com.CleaningService.JavaProject.utils.CategoryDAO;
import com.CleaningService.JavaProject.utils.CategoryDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    
    @Bean
    public CategoryDAO categoryDAO() {
        return new CategoryDAOImpl();
    }
}