package ru.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class PatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatternsApplication.class, args);
		System.out.println("Application started in http://localhost:8000");
		System.out.println("Swagger UI: http://localhost:8000/swagger-ui/index.html");
	}

}
