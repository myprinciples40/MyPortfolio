package com.fastcampus.reflectionapi.di2;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Purpose: Practice for understanding Reflection API
 * Features: - Code to change the car's information using AppConfig.java without modifying the code inside the class
 *           - Easy to change code when something needs to be changed
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-7-1
 * Modification Date:
 */

class Car {
    @Autowired Engine engine;
//    @Resource(name="door")
    @Resource
    Door door;

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}
class SportsCar extends Car {
    @Override
    public String toString() {
        return "SportsCar{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}
class Engine {}
class Door {}

public class Main {
    public static void main(String[] args) {
        // AppContext(Class clazz) - Configuration file (Specify a Java class)
        AppContext ac = new AppContext(AppConfig.class);
        Car car = (Car) ac.getBean("car"); //byName
        Car car2 = (Car) ac.getBean(Car.class); //byType
        Engine engine = (Engine) ac.getBean("engine"); //byName
        Door door = (Door) ac.getBean(Door.class); //byType

        // Set up relationships between beans manually vs Automatically(@Autowired)
//        car.setEngine(engine); // car.engine = engine
//        car.setDoor(door);

        System.out.println("car = " + car);
        System.out.println("car2 = " + car2);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);


    }
}
