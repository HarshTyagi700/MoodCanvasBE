package com.harsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class AnalyticsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsMsApplication.class, args);
	}

}
