package com.fastcampus.jpaptractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.persistence.EntityManagerFactory;

/**
 * Purpose: Practice JPA
 * Features: Connecting to RDB using HIBERNATE and JPA Setting
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-13
 * Modification Date:
 */

@SpringBootApplication
public class JpaPtracticeApplication implements CommandLineRunner {
	@Autowired
	//EntityManagerFactory injection
	EntityManagerFactory emf;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(JpaPtracticeApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("emf = " + emf);
	}
}
