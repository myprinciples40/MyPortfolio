package com.fastcampus.reflectionapi.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Purpose: Practice for understanding Reflection API
 * Features: Code to change the car's information using AppConfig.java without modifying the code inside the class
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-7-1
 * Modification Date:
 */

public class AppContext {
    Map map = new HashMap();

    // This is a hard-cording.
    AppContext() {
        map.put("car", new SportsCar());
        map.put("engine", new Engine());
        map.put("door", new Door());
    }

    AppContext(Class clazz) {
        Object config = null;
        try {
            config = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Method[] methods = clazz.getDeclaredMethods();

        for(Method m : methods) {
            System.out.println("m = " + m.getName());
            for(Annotation anno : m.getDeclaredAnnotations()) {
                if(anno.annotationType() == Bean.class) {
                    // map.put("car", config.car());
                    try {
                        map.put(m.getName(), m.invoke(config, null));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        // Find @Autowired and handle automatic connections between beans (objects)
        doAutoWired();
        // Find @Resource and handle automatic connections between beans (objects)
        doResource();
    }

    private void doAutoWired() {
        for(Object bean : map.values()) {
            for(Field field : bean.getClass().getDeclaredFields()) { // all variables
                if(field.getAnnotation(Autowired.class) != null) {
                    try {
                        field.set(bean, getBean(field.getType())); // car.engine = obj
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void doResource() {
        for(Object bean : map.values()) {
            for(Field field : bean.getClass().getDeclaredFields()) { // all variables
                if(field.getAnnotation(Resource.class) != null) {
                    try {
                        field.set(bean, getBean(field.getName())); // car.engine = obj
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public Object getBean(Class clazz) {
        for(Object obj : map.values()) {
            if (clazz.isInstance(obj)) {
                return obj;
            }
        }
        return null;
    }

    public Object getBean(String id) {
        return map.get(id);
    }
}
