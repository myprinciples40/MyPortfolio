package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 *  Author: Jinhwan Kim (Jin)
 *  Date created: 2023-06-18
 *  Modification Date:
 */

public class MenuTest {
    @DisplayName("Returns the menu corresponding to the menu name in the menu panel.")
    @Test
    void chooseTest() {
        Menu menu = new Menu(List.of
                    (
                        new MenuItem("Tonkatsu", 8000),
                        new MenuItem("Cold Noodles", 7000),
                        new MenuItem("Dumplings", 5800))
                    );

        MenuItem menuItem = menu.choose("Tonkatsu");

        assertThat(menuItem).isEqualTo(new MenuItem("Tonkatsu", 8000));
        //Failed. The reason is that the Cool class must have an Equals hash code when comparing objects (cook objects).
    }

    @DisplayName("Returns an exception when selecting a menu that is not on the menubar.")
    @Test
    void chooseTest2() {
        Menu menu = new Menu(List.of
                    (
                        new MenuItem("Tonkatsu", 8000),
                        new MenuItem("Cold Noodles", 7000),
                        new MenuItem("Dumplings", 5800))
                    );

        assertThatCode(() -> menu.choose("Chicken"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid menu name.");
    }
}
