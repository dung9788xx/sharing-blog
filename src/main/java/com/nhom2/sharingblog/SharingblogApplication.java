package com.nhom2.sharingblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SharingblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharingblogApplication.class, args);
	}

}
