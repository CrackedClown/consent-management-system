package com.cms.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
	}
)
public class CmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmApplication.class, args);
	}

}
