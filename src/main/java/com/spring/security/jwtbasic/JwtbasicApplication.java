package com.spring.security.jwtbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtbasicApplication {
	public static void main(String[] args) {

		SpringApplication.run(JwtbasicApplication.class, args);
	}

}
