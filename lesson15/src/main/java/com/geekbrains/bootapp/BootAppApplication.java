package com.geekbrains.bootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.geekbrains.bootapp.repositories"})
@EntityScan(basePackages = {"com.geekbrains.bootapp.entities"})
public class BootAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootAppApplication.class, args);
	}
}
