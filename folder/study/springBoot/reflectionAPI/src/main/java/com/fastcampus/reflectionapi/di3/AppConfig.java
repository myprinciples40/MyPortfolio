package com.fastcampus.reflectionapi.di3;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Purpose: Practice for understanding Reflection API
 * Features: Code to change the car's information using AppConfig.class without modifying the code inside other class
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-02
 * Modification Date:
 */

// to indicate that it is a configuration class
@Configuration
// Looks for classes in that package with the @Component annotation.
// Second: When multiple people develop together, use @Component for the class you're developing.
@ComponentScan
public class AppConfig {
    // First method
//    @Bean
//    Car car() {
//        return new Car();
//    }
//    @Bean
////    @Scope("singleton") // basic option (Omitted)
//    @Scope("prototype") // To receive a new object
//    Engine engine() {
//        return new Engine();
//    }
//
//    @Bean
//    Door door() {
//        return new Door();
//    }
}
