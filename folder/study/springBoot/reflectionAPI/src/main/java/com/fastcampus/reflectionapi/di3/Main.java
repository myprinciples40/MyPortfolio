package com.fastcampus.reflectionapi.di3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * Purpose: Practice for understanding Reflection API
 * Features: Code to change the car's information using AppConfig.java without modifying the code inside the class
 * About: ApplicationContext and Spring Annotation
 * <p>
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-7-2
 * Modification Date:
 */

@Component
class Car {
    // Search for beans by type and automatically inject them into reference variables
//    @Autowired
//    Engine[] engine;

    // Search for beans byName
//    @Resource(name="engine")

    //    @Resource(name="superEngine") //first
//    @Autowired //second: byType
//    @Qualifier("superEngine") //Among the beans found, inject superEngine with the name (Same as above)
    @Inject // Nearly same with @Autowired (it doesn't have required=false)
    Engine engine;

    //    @Autowired
    @Resource(name = "door")
    Door door;

    // You can't omit @Autowired when you have multiple constructors.
//    public Car () {}

    // When the bean is created, the injected
    // You can omit @Autowired if you have only one constructor with parameters
//    @Autowired
//    public Car(Engine engine, Door door) {
//        this.engine = engine;
//        this.door = door;
//    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

//@Component
class Engine {
}

//@Component("superEngine")
//@Component
//class SuperEngine extends Engine {
//}

@Component
class TurboEngine extends Engine {
}

@Component
class Door {
}

public class Main {
    public static void main(String[] args) {
        // Generate ApplicationContext - AC's configuration file is called AppConfig.class JavaConfig.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //        Car car = (Car) ac.getBean("car"); // byName - Lookup an object (bean)
        Car car = ac.getBean("car", Car.class); // Same as above
        // In AppConfig.java, append @Scope("prototype") to make it a new object
//            Engine engine = ac.getBean(Engine.class); // byType
//            Engine engine2 = ac.getBean(Engine.class);
//            Engine engine3 = ac.getBean(Engine.class);
        System.out.println("car = " + car);
//            System.out.println("engine = " + engine);
//            System.out.println("engine2 = " + engine2);
//            System.out.println("engine3 = " + engine3);
        System.out.println();

        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount());
        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames()));
        System.out.println("ac.containsBeanDefinition(\"engine\") = " + ac.containsBeanDefinition("engine"));
        System.out.println("ac.isSingleton(\"car\") = " + ac.isSingleton("car"));
//            System.out.println("ac.isPrototype(\"engine\") = " + ac.isPrototype("engine"));
        System.out.println();

        // @Value annotation test
        SysInfo info = ac.getBean(SysInfo.class);
        System.out.println("info = " + info);

        Properties prop = System.getProperties();
        System.out.println("prop = " + prop);

        Map<String, String> env = System.getenv();
        System.out.println("env = " + env);
        System.out.println();


    }
}
