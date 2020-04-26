package com.home.samplerestserver.commonserver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.accesslog.AccessLogBuilder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Use the JDK http server to handle REST calls.
 */
public class RestServer {
    private static final Logger LOG = Logger.getLogger(RestServer.class.getName());

    private static HttpServer httpServer = null;

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

        try {
            if (httpServer == null) {
                ResourceConfig rc = new ResourceConfig().packages("com.home.samplerestserver.commonserver").property("jersey.config.server.tracing.type ", "ALL");
                httpServer = GrizzlyHttpServerFactory.createHttpServer(new URI("http://localhost:8080/rest"), rc, false);
                ServerConfiguration serverConfiguration = httpServer.getServerConfiguration();

                final AccessLogBuilder builder = new AccessLogBuilder("./logs/access.log");
                builder.instrument(serverConfiguration);

                httpServer.start();
            }
        }
        catch (URISyntaxException | IOException ex) {
            LOG.error("StartServices failed: " + ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Stop the http server.
     *
     * @return true in any case so far
     */
    public static boolean stopServices() {
        LOG.info("Enter stopServices");

        if (httpServer != null) {
            httpServer.shutdownNow();
            httpServer = null;
        }

        LOG.info("Http server stopped");

        return true;
    }
}
