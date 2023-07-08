package org.example;

import java.util.Objects;

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

// GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
public class RequestLine {
    private final String method; // GET
    private final String urlPath; // /calculate
    // Available because we created the QueryStrings class to create the object for us
    private QueryStrings queryStrings; // operand1=11&operator=*&operand2=55

    public RequestLine(String method, String urlPath, String queryStrings) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStrings = new QueryStrings(queryStrings);
    }

    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];
        String[] urlPathTokens = tokens[1].split("\\?");
        this.urlPath = urlPathTokens[0];

        if (urlPathTokens.length == 2) {
            this.queryStrings = new QueryStrings(urlPathTokens[1]);
        }
    }

    // HttpRequest Class has a request inherited from the isGetRequest() method
    public boolean isGetRequest() {
        return "GET".equals(this.method);
    }
    // HttpRequest Class inherited from matchPath() method
    public boolean matchPath(String requestPath) {
        return urlPath.equals(requestPath);
    }
    public QueryStrings getQueryStrings() {
        return this.queryStrings;
    }

    // Include equals() and hashCode() methods: these must be included when comparing objects to objects.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryStrings, that.queryStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryStrings);
    }
}
