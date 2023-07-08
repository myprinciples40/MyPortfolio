package org.example.di;

import javassist.tools.reflect.Reflection;
import org.example.annotation.Inject;
import org.example.controller.UserController;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Purpose: Practice for making a DI Framework
 * Features: Implementing a BeanFactory inside an ApplicationContext
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-07
 * Modification Date:
 */

public class BeanFactory {
    private final Set<Class<?>> preInstantiatiedClazz;
    // A field name with an object of class type as key and a created instance as value.
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatiedClazz) {
        this.preInstantiatiedClazz = preInstantiatiedClazz;
        initialize();
    }

    // Methods that initialize the beans variable
    private void initialize() {
        for (Class<?> clazz : preInstantiatiedClazz) {
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    // UserController
    // UserService
    private Object createInstance(Class<?> clazz) {
        // Constructor lookup
        Constructor<?> constructor = findConstructor(clazz);

        // parameter
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : constructor.getParameterTypes()) {
            // Methods for getting an instance of a class object - UserService
            parameters.add(getParameterByClass(typeClass));
        }

        // generate instance
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    // Methods that take an object of a class type and get its constructor
    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);

        if(Objects.nonNull(constructor)) {
            return constructor;
        }
        // return a constructor with the first inject attached
        return clazz.getConstructors()[0];
    }

    private Object getParameterByClass(Class<?> typeClass) {
        Object instanceBean = getBean(typeClass);

        if (Objects.nonNull(instanceBean)) {
            return instanceBean;
        }

        // Using a recursive function to create a UserService instance
        return createInstance(typeClass);
    }

    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }
}
