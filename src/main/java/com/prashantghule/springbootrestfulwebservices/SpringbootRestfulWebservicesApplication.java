package com.prashantghule.springbootrestfulwebservices;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SpringBoot RestAPI Documentation",
								description = "SpringBoot RestAPI Documentation",
								contact = @Contact(
										name = "Prashant Ghule",
										email = "ghuleprashant3097@gmail.com",
										url = "https://github.com/ghuleprashant"
								)
								),
					externalDocs = @ExternalDocumentation(
							description = "Spring Boot User Management Documentation",
							url = "https://github.com/ghuleprashant"
					))
public class SpringbootRestfulWebservicesApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
