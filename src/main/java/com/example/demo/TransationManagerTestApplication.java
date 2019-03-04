package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TransationManagerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransationManagerTestApplication.class, args);
	}

}
