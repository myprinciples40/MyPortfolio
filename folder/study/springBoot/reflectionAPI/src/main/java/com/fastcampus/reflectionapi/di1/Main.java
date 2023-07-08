package com.fastcampus.reflectionapi.di1;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.util.Properties;

/**
 * Purpose: Practice for understanding Reflection API
 * Features: Code to change the car's information using config.txt without modifying the code inside the class
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-06-25
 * Modification Date:
 */

class Car {
    // injection objects when @Autowired is attached
    @Autowired
    Engine engine;
    @Autowired Door door;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Door getDoor() {
        return door;
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
class SportsCar extends Car {}
class Truck extends Car {}
class Engine {}
class TurboEngine extends Engine {}
class Door {}
class Trunk extends Door {}

public class Main {
    public static void main(String[] args) throws Exception {
        Car car = (Car) getObject("car");
        Engine engine = (Engine) getObject("engine");
        Door door = (Door) getObject("door");
        System.out.println("Car = " + car);
        System.out.println("Engine = " + engine);
        System.out.println("door = " + door);
    }

    static Object getObject(String key) throws Exception {
        Properties prop = new Properties();
        //Class clazz = null; // Gets the class object corresponding to the specified class name.

        // You only need to modify the config.txt file without touching any source code.
        prop.load(new FileReader("config.txt"));
        String className = prop.getProperty(key); // Returns the value of the specified key
        Class clazz = Class.forName(className); // Gets the class object corresponding to the specified class name.

        return clazz.newInstance(); // new SportCar();
    }

    static Car getCar() {
        return new SportsCar();
    }
}
