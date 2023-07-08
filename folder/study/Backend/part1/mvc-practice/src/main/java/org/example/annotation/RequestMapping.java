package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Purpose: Practice for understanding Reflection API
 * Features: Find and print all classes that have the @Controller annotation set.
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-02
 * Modification Date:
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    // For get requests, return a value() corresponding to the URL path
    String value() default "";

    // The method to handle the GET or POST
    RequestMethod[] method() default {};
}
