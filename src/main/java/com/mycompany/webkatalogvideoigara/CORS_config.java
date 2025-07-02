/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webkatalogvideoigara;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author uros
 */
@Configuration
@EnableWebMvc
public class CORS_config implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry reg){
        reg.addMapping("/**")
                .allowedOrigins("https://localhost:4430","http://localhost:3000")
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
