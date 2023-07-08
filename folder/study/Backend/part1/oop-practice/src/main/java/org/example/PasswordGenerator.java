package org.example;

// Annotation for writing lambdas
@FunctionalInterface
public interface PasswordGenerator {

    String generatePassword();
}
