package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @DisplayName("Reset the password.")
    @Test
    void passwordTest() {
        // given
        User user = new User();

        // when
//        user.initPassword(new CorretedFixedPasswordGenerator()); // original code
        user.initPassword(() -> "abcdefgh"); // CorrectedFixedPasswordGenerator

        // then
        assertThat(user.getPassword()).isNotNull();
    }

    @DisplayName("The password is not initialized because it does not meet the requirements.")
    @Test
    void passwordTest2() {
        // given
        User user = new User();

        // when
//        user.initPassword(new WrongFixedPasswordGenerator()); // original code
        user.initPassword(() -> "ab"); // Use lambdas after putting only @FunctionalInterface in the interface

        // then
        assertThat(user.getPassword()).isNull();
    }
}
