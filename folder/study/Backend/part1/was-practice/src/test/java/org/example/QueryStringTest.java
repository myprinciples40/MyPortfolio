package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-06-26
 * Modification Date:
 */

// operand1=11&operator=*&operand2=55
public class QueryStringTest {
    // need a List<QueryString>
    @Test
    void createTest() {
        QueryString queryString = new QueryString("operand", "11");
        assertThat(queryString).isNotNull();
    }
}
