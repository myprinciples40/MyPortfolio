package org.example.di;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Purpose: Practice for making a DI Framework
 * Features: Implementing a BeanFactory inside an ApplicationContext
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-07
 * Modification Date:
 */

// used Top-down development method
class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    // Methods that are called before the test method is called
    @BeforeEach
    void setUp() {
        reflections = new Reflections("org.example");
        // UserController, UserService will return
        Set<Class<?>> preInstantiatiedClazz = getTypesAnnotatedWith(Controller.class, Service.class);
        beanFactory = new BeanFactory(preInstantiatiedClazz);
    }

    // Change the parameter to accept multiple arguments of type Annotation
    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation: annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);

        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }
}