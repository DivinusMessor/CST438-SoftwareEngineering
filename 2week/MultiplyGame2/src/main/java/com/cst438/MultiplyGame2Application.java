package com.cst438;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
public class MultiplyGame2Application {

	public static void main(String[] args) {
		SpringApplication.run(MultiplyGame2Application.class, args);
	}

}
