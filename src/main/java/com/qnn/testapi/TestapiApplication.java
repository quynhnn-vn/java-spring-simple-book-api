package com.qnn.testapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication = @Component + @ComponentScan + @EnableAutoConfiguration
@SpringBootApplication
public class TestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestapiApplication.class, args);
	}

}
