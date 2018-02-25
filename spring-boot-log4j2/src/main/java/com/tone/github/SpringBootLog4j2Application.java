package com.tone.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootLog4j2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLog4j2Application.class, args);
	}
}
