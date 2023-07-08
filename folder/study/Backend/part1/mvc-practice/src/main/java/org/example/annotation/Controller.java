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
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}
