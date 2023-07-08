package com.fastcampus.reflectionapi.di1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Purpose: Practice for understanding Reflection API
 * Features: Code to change the car's information using config.txt without modifying the code inside the class
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-06-25
 * Modification Date:
 */

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        // 3 Ways to Get a Class Object
        Class carClass = car.getClass(); // 1. get the Class object (blueprint) from the object
        carClass = Car.class; // 2. Get a Class object (blueprint) from an object literal
        carClass = Class.forName("com.fastcampus.reflectionapi.di1.Car"); // 3. get the Class object (schematic) with the class name - you'll also need to write down the package.

        // 1. Create an object from a blueprint object
        Car car2 = (Car)carClass.newInstance();
        System.out.println("car2 = " + car2);
        System.out.println();

        // 2. get a list of member variables (fields) and methods declared in the class
        Field[] mvArr = carClass.getDeclaredFields();
//        Method[] methodArr = carClass.getDeclaredMethods();
        Method[] methodArr = carClass.getMethods();

        for(Field mv : mvArr) System.out.println(mv.getName());
        for(Method method : methodArr) System.out.println(method.getName());
        System.out.println();

        Method method = carClass.getMethod("setEngine", Engine.class); // setEngine(Engine engine)
        // Code written using the reflection API
        method.invoke(car, new Engine()); // car.setEngine(new Engine()); (기존 방식) / car.getType().newInstance();
        System.out.println("car = " + car);
        System.out.println();

        // 3. append set to mv to call the setter
        for(Field mv: mvArr) {
            System.out.println("mv = " + mv);
            String methodName = "set" + StringUtils.capitalize(mv.getName()); // "set" + "Engine" = "setEngine", "set" + "Door" = "setDoor"
            System.out.println("methodName = " + methodName);
            method = carClass.getMethod(methodName, mv.getType());// carClass.getMethod("setEngine", Engine.class); This is a hard-cording
            method.invoke(car, mv.getType().newInstance()); // car.setEngine(new Engine()); , car.setDoor(new Door());
        }
        System.out.println("car = " + car);
        System.out.println();

        // 4. make sure @Autowired is attached to the mv
        for(Field mv: mvArr) {
             Annotation[] annoArr = mv.getDeclaredAnnotations();
             for(Annotation anno: annoArr) {
                 System.out.println("mv.getName() = " + mv.getName());
                 System.out.println("anno.annotationType().getSimpleName() = " + anno.annotationType().getSimpleName());
                 System.out.println(anno.annotationType() == Autowired.class);
             }
        }
        System.out.println();

        // This is used a lot, so practice!!!(important!!!)
        // 5.(3+4) Call a setter on the mv attached to @Autowired
        // (it will automatically put the object in - this is called auto-injection)
        car = new Car();

        for(Field mv: mvArr) {
            Annotation[] annoArr = mv.getDeclaredAnnotations();
            for(Annotation anno: annoArr) {
                System.out.println("mv.getName() = " + mv.getName());
                if(isaBoolean(anno)) {
                    //setter 호출
                    String methodName = "set" + StringUtils.capitalize(mv.getName());
                    method = carClass.getMethod(methodName, mv.getType());
                    method.invoke(car, mv.getType().newInstance()); // car.setEngine(new Enging());
                }
            }
        }
        System.out.println("car = " + car);
    }

    private static boolean isaBoolean(Annotation anno) {
        return anno.annotationType() == Autowired.class;
    }
}
