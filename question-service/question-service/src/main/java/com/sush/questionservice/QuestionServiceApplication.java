package com.sush.questionservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(info = @Info(
		title = "Spring Boot Question App microservice REST API's that supports the QUIZ App",
		description = "Spring Boot Question App microservice REST API's that are internally called by Quiz microservice app",
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
@SpringBootApplication
public class QuestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceApplication.class, args);
	}

}
