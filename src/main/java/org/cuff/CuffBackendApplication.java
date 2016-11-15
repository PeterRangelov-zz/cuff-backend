package org.cuff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication @ComponentScan(basePackages="org.cuff.controllers")
public class CuffBackendApplication {

	public static void main(String[] args) {
		System.out.println("STARTING APPLICATION");
		SpringApplication.run(CuffBackendApplication.class, args);
	}
}
