package com.infy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class PinterestGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinterestGatewayApplication.class, args);
	}

}
