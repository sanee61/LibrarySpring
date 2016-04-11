package com.sanaz.librarySpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sanaz")
public class LibrarySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySpringApplication.class, args);
	}
}
