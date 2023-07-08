package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(CustomerWebApplicationServer.class);
    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        /**
         * Step2 - Each time a user request comes in, we create a new Thread to handle the user request.
         * Each time it is created, it is allocated independent memory on the stack,
         * which can increase memory consumption and degrade performance if many new requests are generated.
         * In addition, when the number of concurrent users increases, many threads are generated,
         * which increases the number of CPU contest switches and significantly increases CPU and memory usage.
         * In the worst case scenario, the server's resources could overwhelm it and it could go down.
         * Therefore, we need to apply the concept of Thread Pools,
         * where a fixed number of threads are already created and recycled to ensure a reliable service.
         */

        logger.info("[ClientRequestHandler] new client {} started.", Thread.currentThread().getName());
        // An attempt to see what the HTTP protocol looks like
        try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
            // Replace InputStream with Reader to read it line by line
            BufferedReader br = new BufferedReader((new InputStreamReader(in, StandardCharsets.UTF_8)));
            DataOutputStream dos = new DataOutputStream(out);

            HttpRequest httpRequest = new HttpRequest(br);

            // GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
            if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                // If it matches, get the QueryString via the first-level collection.
                QueryStrings queryStrings = httpRequest.getQueryStrings();

                int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                String operator = queryStrings.getValue("operator");
                int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
                // From here, using the HttpResponse class and its associated
                byte[] body = String.valueOf(result).getBytes();

                HttpResponse response = new HttpResponse(dos);
                response.response200Header("application/json", body.length);
                response.responseBody(body);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
