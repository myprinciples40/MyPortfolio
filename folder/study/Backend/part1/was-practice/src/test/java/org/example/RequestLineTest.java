package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * HttpRequest
 * - RequestLIne (Data to extract) - GET /calculate?operand1=11&operator=-&operand2=55 HTTP/1.1
 * - HttpMethod
 * - path
 * - queryString
 * - protocol/version (Omitted here)
 * - HeaderW
 * - Body (Only required for Post)
 *
 *  * Author: Jinhwan Kim (Jin)
 *  * Date created: 2023-06-26
 *  * Modification Date: 2023-06-30
 */

public class RequestLineTest {
    @Test
    void create() {
        RequestLine requestLine = new RequestLine("GET /calculate?operand1=11&operator=-&operand2=55 HTTP/1.1");

        assertThat(requestLine).isNotNull();
        assertThat(requestLine).isEqualTo(new RequestLine("GET", "/calculate", "operand1=11&operator=-&operand2=55"));
    }
}
