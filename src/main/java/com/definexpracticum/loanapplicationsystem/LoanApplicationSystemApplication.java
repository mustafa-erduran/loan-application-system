package com.definexpracticum.loanapplicationsystem;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@SecurityScheme(name = "Bearer Authentication", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT")
public class LoanApplicationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplicationSystemApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenApi(@Value("${application-description}") String description,
								 @Value("${application-version}") String version){
		return new OpenAPI()
				.info(new Info()
						.title("Loan Application System API")
						.version(version)
						.description(description)
						.license(new License().name("Loan Application System API License")));

	}

}
