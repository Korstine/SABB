package fr.sabb.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SabbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SabbApplication.class, args);
	}
}
