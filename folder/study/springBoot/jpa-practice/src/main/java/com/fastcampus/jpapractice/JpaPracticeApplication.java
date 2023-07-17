package com.fastcampus.jpapractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

@SpringBootApplication
public class JpaPracticeApplication implements CommandLineRunner {
    @Autowired
    EntityManagerFactory emf;


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(JpaPracticeApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        EntityManager em = emf.createEntityManager();
        System.out.println("em = " + em);
        EntityTransaction transaction = em.getTransaction();

        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("Kim");
        user.setEmail("aaa@aaa.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());

        transaction.begin(); // Start the transaction
        // 1. Save
        em.persist(user); // Perpetuate the user entity to em (save)

        // 2. Update
        user.setPassword("4321"); // PersistenceContext detects a change. update
        user.setEmail("bbb@bbb.com");
        transaction.commit(); // Finish the transaction (reflect to DB)

        // 3. Search
        User user2 = em.find(User.class, "aaa"); // not retrieve DB if in em
        System.out.println("user==user2 = " + (user == user2));
        User user3 = em.find(User.class, "bbb"); // null, not in DB
        System.out.println("user3 = " + user3);

        // 4. Delete
        transaction.begin();
        em.remove(user); // remove user entity from em
        transaction.commit();
    }
}
