package com.mytodolist.mytodolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;

@SpringBootApplication(exclude = SqlInitializationAutoConfiguration.class)
public class MytodolistApplication {
	//https://spring.io/guides/gs/serving-web-content
	public static void main(String[] args) {
		SpringApplication.run(MytodolistApplication.class, args);
	}

}
