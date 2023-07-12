package com.fastcampus.reflectionapi.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.ObjectInputFilter;
import java.util.Arrays;

/**
 * Purpose: Practice for understanding AOP
 * Features: Adding advice type with around only to methods with add in the MyMath class
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-12
 * Modification Date:
 */

//Add the spring-boot-starter-aop dependency to your pom.xml to enable it
public class AopMain2 {
    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(Config.class);
        MyMath mm = ac.getBean("myMath", MyMath.class);
        mm.add(3, 5);
        mm.add(1, 2, 3);
        System.out.println("mm.multiply(3, 4) = " + mm.multiply(3, 4));
        mm.subtract(4, 2);
    }
}

@EnableAspectJAutoProxy //Setting up AOP automatically
@ComponentScan //Component scanning automatically
@Configuration //java configuration
class Config {

}

//I didn't register the annotation, so I didn't get the desired result.
@Component
@Aspect
class LoggingAdvice {
    //Make around only apply to methods with add in the MyMath class (pattern)
    @Around("execution(* com.fastcampus.reflectionapi.aop.MyMath.add*(..))")
    public Object methodClassLog(ProceedingJoinPoint pjp) throws Throwable {
        //code to be added to the beginning of target's method
        long start = System.currentTimeMillis();
        //Output the called method name and parameters
        System.out.println("<<[start]" + pjp.getSignature().getName() + Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed(); //Call a method on target

        //code to be added to the end of target's method
        System.out.println("result = " + result);
        System.out.println("[end]>>" + (System.currentTimeMillis() - start) + "ms");
        return result;
    }
}

//Registering a bean
@Component
//Target(The object to which the advice will be added)
class MyMath {
    int add(int a, int b) {
        int result = a + b;
        return result;
    }
    int add(int a, int b, int c) {
        int result = a + b + c;
        return result;
    }
    int subtract(int a, int b) {
        int result = a - b;
        return result;
    }
    int multiply(int a, int b) {
        int result = a * b;
        return result;
    }
}
