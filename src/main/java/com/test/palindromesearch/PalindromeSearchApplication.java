package com.test.palindromesearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PalindromeSearchApplication {


	public static void main(String[] args) {
		SpringApplication.run(PalindromeSearchApplication.class, args);
	}

}
