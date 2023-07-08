package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 *  The password must be at least 8 characters and no more than 12 characters.
 *  Raise an IllegalArgumentException exception if the password is less than 8 characters or more than 12 characters.
 *  You must write test code for the boundary conditions.
 */
public class PasswordValidatorTest {

    @DisplayName("No exception is thrown if the password is at least 8 characters long and no more than 12 characters.")
    @Test
    void validatePasswordTest() {
        assertThatCode(() -> PasswordValidator.validate("serverwizard"))
                .doesNotThrowAnyException();
    }

    @DisplayName("Raise an IllegalArgumentException exception if the password is less than 8 characters or more than 12 characters.")
    @ParameterizedTest // Practice because you'll use it a lot in practice!!!(very important!!!)
    @ValueSource(strings = {"aabbcce", "aabbccddeeffg"}) // Check the boundary values of 7 and 13 characters
    void validatePasswordTest2(String password) {
        assertThatCode(() -> PasswordValidator.validate("aabb"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("at least 8 characters and no more than 12 characters");
    }
}
