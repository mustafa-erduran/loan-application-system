package com.definexpracticum.loanapplicationsystem;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "Bearer Authentication", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT")
public class LoanApplicationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplicationSystemApplication.class, args);
	}


}
