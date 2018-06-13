package com.example.tsunamiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class TsunamiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TsunamiServiceApplication.class, args);
	}
}
