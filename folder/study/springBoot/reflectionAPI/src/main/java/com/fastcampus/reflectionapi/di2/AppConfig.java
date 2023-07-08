package com.fastcampus.reflectionapi.di2;

import org.springframework.context.annotation.Bean;

public class AppConfig {
    // <bean id="car" class="com.fastcampus.ch3.Car"> // XML
    @Bean public Car car() { // the method name is the name of the bean.
        // map.put("car", new Car());
        Car car = new Car();
        return car;
    }
    @Bean public Engine engine() { // the method name is the name of the bean.
        // map.put("engine", new Engine());
        return new Engine();
    }
    @Bean public Door door() { // the method name is the name of the bean.
        // map.put("door", new Door());
        return new Door();
    }
}
