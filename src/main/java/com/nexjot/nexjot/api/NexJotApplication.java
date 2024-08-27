/**
 * NexJotApplication.java
 * Starts the Spring Boot application.
 */
package com.nexjot.nexjot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class of the Spring Boot application.
 */
@SpringBootApplication
public class NexJotApplication {

	/**
	 * This is the main method of the Spring Boot application.
	 * It is the entry point of the application.
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(NexJotApplication.class, args);
	}
}
