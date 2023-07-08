package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-06-26
 * Modification Date:
 */

// Testing a first-class collection class
public class QueryStringsTest {

    @Test
    void createTest() {
        QueryStrings queryStrings = new QueryStrings("operand1=11&operator=*&operand2=55"); // List<QueryString>
        assertThat(queryStrings).isNotNull();
    }
}
