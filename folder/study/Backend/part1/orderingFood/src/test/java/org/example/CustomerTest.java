package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Implementing the process of ordering food at a restaurant
 * 1. Think about what objects make up your domain
 *      ㄴ Guest, Menu(o) - Tonkatsu / Cold Noodles / Dumplings(o), Chef(o), Cooking(o)
 * 2. Think about relationships between objects
 *      ㄴ Guest -- Menu Board
 *      ㄴ Guest -- Chef
 *      ㄴ a chef -- cooking
 * 3. Modeling your domain by abstracting dynamic objects into static types
 *      ㄴ Guest -- Guest type
 *      ㄴ Tonkatsu, Cold Noodles, Dumplings -- Cuisine Type
 *      ㄴ Menubar -- Menubar Type
 *      ㄴ Menu -- Menu Type
 *      ㄴ Dish -- Dish Type
 * 4. Designing collaboration
 * 5. Assign appropriate responsibilities to types that encompass objects
 * 6. Implementing
 *
 *  * Author: Jinhwan Kim (Jin)
 *  * Date created: 2023-06-18
 *  * Modification Date: 2023-06-21
 */

public class CustomerTest {
    @DisplayName("Order the dish with the menu name.")
    @Test
    void orderTest() {
        Customer customer = new Customer();
        Menu menu = new Menu(List.of
                (
                    new MenuItem("Tonkatsu", 8000),
                    new MenuItem("Cold Noodles", 7000),
                    new MenuItem("Dumplings", 5800))
                );
        Cooking cooking = new Cooking();

        assertThatCode(() -> customer.order("Tonkatsu", menu, cooking))
                .doesNotThrowAnyException();

    }
}
