package com.ibrahimayhan.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.ibrahimayhan"})
@ComponentScan(basePackages = {"com.ibrahimayhan"})
@EnableJpaRepositories(basePackages = {"com.ibrahimayhan"})
@SpringBootApplication
public class CarGalleristAppApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(CarGalleristAppApplicationStarter.class, args);
	}

}
