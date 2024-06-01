package com.yspjt.dbafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class) 
public class DbafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbafeApplication.class, args);
	}

}
