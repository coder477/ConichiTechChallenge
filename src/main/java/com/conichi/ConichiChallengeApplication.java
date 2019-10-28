package com.conichi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ConichiChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConichiChallengeApplication.class, args);
	}

}
