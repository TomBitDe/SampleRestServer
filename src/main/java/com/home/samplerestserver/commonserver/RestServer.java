package com.home.samplerestserver.commonserver;

import com.sun.net.httpserver.HttpServer;
import java.net.URI;
import org.apache.log4j.Logger;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Use the JDK http server to handle REST calls.
 */
public class RestServer {
    private static final Logger LOG = Logger.getLogger(RestServer.class.getName());

    private static HttpServer httpServer;

    /**
     * It's not allowed to create an instance.
     */
    private RestServer() {
    }

    /**
     * Create the http server.
     *
     * @return true in any case so far
     */
    public static boolean startServices() {
        LOG.info("Enter startServices");

        ResourceConfig rc = new ResourceConfig().packages("com.home.samplerestserver.commonserver");
        httpServer = JdkHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/rest"), rc);

        return true;
    }

    /**
     * Stop the http server.
     *
     * @return true in any case so far
     */
    public static boolean stopServices() {
        LOG.info("Enter stopServices");

        httpServer.stop(0);
        LOG.info("Http server stopped");

        return true;
    }
}
