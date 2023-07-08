
package org.example;
import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Executable;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Custom Tomcat class

/**
 * HttpRequest
 * - RequestLIne (Data to extract) - GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
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

public class CustomerWebApplicationServer {
    private final int port;

    // Step 3. Create a Thread Pool
    private final ExecutorService executorService = Executors.newFixedThreadPool(10); // Limiting the number of thread pools

    private static final Logger logger = LoggerFactory.getLogger(CustomerWebApplicationServer.class);

    public CustomerWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer] client connected!");

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
//                new Thread(new ClientRequestHandler(clientSocket)).start();

                /**
                 * Step 3. Using the created limited thread pool
                 * I have resolved the issue in Step 2.
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
}


