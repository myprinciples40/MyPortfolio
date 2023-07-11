package com.fastcampus.reflectionapi.di4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * Purpose: Practice for understanding Reflection API
 * Features: Practice on @Conditional and @Import
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-07
 * Modification Date:
 */

class Car {
    @Override
    public String toString() {
        return "Car{}";
    }
}

@Component
@Conditional(TrueCondition.class)
class Engine {
    @Override
    public String toString() {
        return "Engine{}";
    }
}

class SportsCar extends Car {
    @Override
    public String toString() {
        return "SportsCar{}";
    }
}

class SportsCar2 extends Car {
    @Override
    public String toString() {
        return "SportsCar2{}";
    }
}

@Component
@Conditional(OSCondition.class)
class Door {
    @Override
    public String toString() {
        return "Door{}";
    }
}

class TrueCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}

class OSCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
//        System.out.println("System.getProperties() = " + System.getProperties());
//        env.getProperty("sun.jnu.encoding").equals("UTF-8")
        return env.getProperty("mode").equals("test");
    }
}

@Configuration
@Import({Config1.class, Config2.class})
//@Import(MyImportSelect.class)
//@EnableMyAutoConfiguration("test2")
// Java Configuration File
class MainConfig { @Bean Car car() {return new Car();}}
class Config1 { @Bean Car sportsCar() {return new SportsCar();}}
class Config2 { @Bean Car sportsCar() {return new SportsCar2();}}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportSelect.class)
@interface EnableMyAutoConfiguration {
    String value() default "";
}

//Apply different configurations based on conditions
class MyImportSelect implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //Get the class value of EnableMyAutoConfiguration
        AnnotationAttributes attr = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableMyAutoConfiguration.class.getName()));
        String mode = attr.getString("value");
        if(mode.equals("test")) {
            return new String[] {Config1.class.getName()};
        }
        else {
            return new String[] {Config2.class.getName()};
        }
    }
}

@EnableConfigurationProperties({MyProperties.class})
//@SpringBootApplication // Same as the three annotations below
@Configuration // @SpringBootConfiguration // Same as @Configuration
// @EnableAutoConfiguration commented out because no DB settings are currently in place
// @EnableAutoConfiguration // Same as @AutoConfigurationPackage + @Import (Use when embedding a configuration file in another configuration file)
@ComponentScan
public class Main implements CommandLineRunner {
    @Autowired
    MyProperties prop; //instance variable

    @Autowired
    ApplicationContext ac;

    //the second method
    //the same role as the main method (without static)
    public void run(String... args) throws Exception { //instance method - automatic injection
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        Arrays.sort(beanDefinitionNames); // Sort an array containing an empty list
        Arrays.stream(beanDefinitionNames) // Convert an array to a stream
                .filter(b -> !b.startsWith("org")) // Filter out bean's name that doesn't have org attached
                .forEach(System.out::println); // Pull out the elements of the stream one by one and output

        System.out.println("prop.getEmail() = " + prop.getEmail());
        System.out.println("prop.getDomain() = " + prop.getDomain());
    }

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(Main.class, args); // only set one here
//        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class, Config1.class, Config2.class); // AC with Java Settings
//        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class); // AC with Java Settings

//        System.out.println("ac = " + ac);

//        System.out.println("ac.getBean(\"sportsCar\") = " + ac.getBean("sportsCar"));

        //the first method
//        MyProperties prop = ac.getBean(MyProperties.class); //Manually search bean and inject
//        System.out.println("prop.getDomain() = " + prop.getDomain());
//        System.out.println("prop.getEmail() = " + prop.getEmail());

    }
    // Because this is a configuration file, the @Bean is registered with ac.

    @Bean
    MyBean myBean() {
        return new MyBean();
    }
}

class MyBean {}

