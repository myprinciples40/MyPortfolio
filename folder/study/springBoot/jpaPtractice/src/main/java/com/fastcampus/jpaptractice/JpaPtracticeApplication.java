
package com.fastcampus.jpaptractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

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
		EntityManager em = emf.createEntityManager();
		System.out.println("emf = " + emf);
		System.out.println("em = " + em);
		EntityTransaction tx = em.getTransaction();

		User user = new User();
		user.setId("aaaa");
		user.setPassword("1234");
		user.setName("Kim");
		user.setEmail("aaa@aaa.com");
		user.setInDate(new Date());
		user.setUpDate(new Date());

		tx.begin(); //start the transaction
		//1.Save
		em.persist(user); //Perpetuate (store) the user entity in EM
		em.persist(user); //If you save the same user entity multiple times, it will only be inserted once.

		//2.Update
		user.setPassword("4321"); //PersistenceContext detects a change. update
		user.setEmail("bbb@bbb.com");
		tx.commit(); //finish the transaction(Reflect to DB)

		//3.Find
		User user2 = em.find(User.class, "aaaa"); //not retrieve DB if in em
		System.out.println("user==user2 = " + (user == user2));
		User user3 = em.find(User.class, "bbbb"); //If not in em, use the DB search
		System.out.println("user3 = " + user3); //null, Not in DB

		//4.Delete
		tx.begin();
		em.remove(user); //Delete the user entity from em
		tx.commit();
	}
}
