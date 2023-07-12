package com.fastcampus.reflectionapi.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Purpose: Practice for understanding AOP
 * Features: Isolating common code and adding code with conditions in the MyClass class
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-12
 * Modification Date:
 */

public class AopMain {
    public static void main(String[] args) throws Exception {
        Class myClass = Class.forName("com.fastcampus.reflectionapi.aop.MyClass");
        Object o = myClass.newInstance();

        MyAdvice myAdvice = new MyAdvice();

        for(Method m: myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, o, null);
        }
    }
}

class MyAdvice {
    Pattern p = Pattern.compile("a.*"); //Strings starting with a

    boolean matches(Method m) { //Tells you if a given method matches a pattern
        Matcher matcher = p.matcher(m.getName());
        return matcher.matches();
    }
    void invoke(Method m, Object obj, Object... args) throws Exception {
        //first method
//        if (matches(m))
        //second method
        if (m.getAnnotation(Transactional.class) != null)
            System.out.println("[before]{"); //Code that will be common
        m.invoke(obj, args); //Calls to aaa(), aaa2(), and bbb()

        if (m.getAnnotation(Transactional.class) != null)
            System.out.println("}[after]"); //Code that will be common
    }
}

class MyClass {
    //Add only methods with annotations
    @Transactional
    void aaa() {
        System.out.println("aaa() is called.");
    }

    void aaa2() {
        System.out.println("aaa2() is called.");
    }
    void bbb() {
        System.out.println("bbb() is called.");
    }
}