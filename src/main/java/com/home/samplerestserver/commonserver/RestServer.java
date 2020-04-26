package com.home.samplerestserver.commonserver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.accesslog.AccessLogBuilder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Use the Grizzly http server to handle REST calls.
 */
public class RestServer {
    private static final Logger LOG = Logger.getLogger(RestServer.class.getName());

    private static final String BASE_URI;
    private static final String PROTOCOL;
    private static final Optional<String> HOST;
    private static final String PATH;
    private static final Optional<String> PORT;

    static {
        PROTOCOL = "http://";
        HOST = Optional.ofNullable(System.getenv("HOSTNAME"));
        PORT = Optional.ofNullable(System.getenv("PORT"));
        PATH = "rest";
        BASE_URI = PROTOCOL + HOST.orElse("localhost") + ":" + PORT.orElse("8080") + "/" + PATH + "/";
    }

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
                httpServer = GrizzlyHttpServerFactory.createHttpServer(new URI(BASE_URI), rc);
                ServerConfiguration serverConfiguration = httpServer.getServerConfiguration();

                final AccessLogBuilder builder = new AccessLogBuilder("./logs/access.log");
                builder.instrument(serverConfiguration);

                httpServer.start();
                LOG.info("Jersey app started with WADL available at " + BASE_URI + "application.wadl");
            }
            else {
                LOG.info("Services already running. No need to start again");
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
            LOG.info("Http server stopped");
        }
        else {
            LOG.info("Http server already stopped. No need to stop again");
        }

        return true;
    }
}
