package com.harsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
@PropertySource(value="classpath:messages.properties")
public class PinboardMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinboardMsApplication.class, args);
	}

}
