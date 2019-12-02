package com.geekbrains.bootapp.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:private.properties")
@ComponentScan("com.geekbrains.bootapp")
public class AppConfig implements WebMvcConfigurer {
}