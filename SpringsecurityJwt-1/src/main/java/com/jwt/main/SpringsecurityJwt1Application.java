package com.jwt.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@ComponentScan("com.jwt")
@EntityScan("com.jwt.entity")
@EnableJpaRepositories("com.jwt.repository")
@SpringBootApplication
public class SpringsecurityJwt1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityJwt1Application.class, args);
	}

}
