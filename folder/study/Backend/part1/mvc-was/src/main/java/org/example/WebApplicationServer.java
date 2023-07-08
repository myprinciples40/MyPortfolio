package org.example;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Purpose: Practice for a mvc controller
 * Features: Implementing the DispatcherServlet that receives all requests, takes the urlPath, finds the appropriate controller
 * Processing order:
 *      1. handler Mapping
 *      2. Selecting a handler
 *      3. Find an adapter that supports that handler
 *      4. Selecting and rendering a view with ViewResolver
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-05
 * Modification Date:
 */

public class WebApplicationServer {
    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    public static void main(String[] args) throws Exception {
        String webappDirLocation = "webapps/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        log.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}