package com.sush.quizservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(
		title = "Spring Boot Quiz App REST API's",
		description = "Spring Boot Quiz App for User to create Quiz,Get a quiz test, Submit a Quiz",
		version = "v1.0",
		contact = @Contact(
				name = "Sushodhan",
				email = "sushodhanputtur98@gmail.com"

		)
			),
		externalDocs = @ExternalDocumentation(
				description = "You can find the link below for the git hub repository",
				url = "www.github.com"
		)

)
public class QuizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceApplication.class, args);
	}

}
