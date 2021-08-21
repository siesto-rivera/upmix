package com.example.upmix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UpmixApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpmixApplication.class, args);
	}

}
