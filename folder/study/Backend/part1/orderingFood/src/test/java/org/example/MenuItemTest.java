package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 *  Author: Jinhwan Kim (Jin)
 *  Date created: 2023-06-18
 *  Modification Date:
 */

public class MenuItemTest {
    @DisplayName("Create a menu item.")
    @Test
    void createTest() {
        assertThatCode(() -> new MenuItem("Dumplings", 5800))
                .doesNotThrowAnyException();
    }
}
