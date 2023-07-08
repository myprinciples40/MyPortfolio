package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *  Author: Jinhwan Kim (Jin)
 *  Date created: 2023-06-18
 *  Modification Date:
 */

public class CookingTest {
    @DisplayName("Create a food that corresponds to a menu.")
    @Test
    void makeCookTest() {
        Cooking cooking = new Cooking(); // Create a Chef object
        MenuItem menuItem = new MenuItem("Tonkatsu", 8000); // Create menu items to send to chefs

        Cook cook = cooking.makeCook(menuItem); // Deliver a menu while asking a chef to make a dish

        assertThat(cook).isEqualTo(new Cook("Tonkatsu", 8000));
        //Failed. The reason is that the Cool class must have an Equals hash code when comparing objects (cook objects).
    }
}
