package com.home.samplerestserver.commonserver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Use the Grizzly http server to handle REST calls.
 */
public class RestServer implements RestServerMBean {
    private static final Logger LOG = LogManager.getLogger(RestServer.class.getName());

    private static final String HOST_DEFAULT = "localhost";
    private static final String PORT_DEFAULT = "8080";
    private static final String PROTOCOL = "http://";
    private static final String PATH = "rest";

    private String baseURI;
    private String host;
    private String port;

    private volatile boolean running;

    private HttpServer httpServer = null;

    public RestServer() {
        LOG.trace("--> RestServer");
        host = System.getProperty("host", HOST_DEFAULT);

        port = System.getProperty("port", PORT_DEFAULT);

        baseURI = PROTOCOL
                + host + ":" + port + "/" + PATH + "/";
        LOG.info("baseURI = {}", baseURI);

        running = false;

        LOG.trace("<-- RestServer");
    }

    /**
     * It's not allowed to clone.
     *
     * @return newer return an Object
     *
     * @throws CloneNotSupportedException always throw it
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        throw new CloneNotSupportedException();
    }

    /**
     * Create the http server.
     *
     * @return true in any case so far
     */
    @Override
    public synchronized boolean startServices() {
        LOG.trace("--> startServices");

        try {
            if (httpServer == null) {
                baseURI = PROTOCOL + getHost() + ":" + getPort() + "/" + PATH + "/";

                ResourceConfig rc = new ResourceConfig()
                        .packages("com.home.samplerestserver.commonserver")
                        .register(CustomLoggingFilter.class)
                        .register(JacksonJsonProvider.class)
                        .property("jersey.config.server.tracing.type", "ALL");

                httpServer = GrizzlyHttpServerFactory
                        .createHttpServer(new URI(baseURI), rc);

                httpServer.start();
                LOG.info("Jersey app started with WADL available at " + baseURI + "application.wadl");
            }
            else {
                LOG.info("Services already running. No need to start again");
            }

            running = true;
        }
        catch (URISyntaxException | IOException ex) {
            LOG.error("StartServices failed", ex);
            running = false;
            return false;
        }
        LOG.trace("<-- startServices");

        return true;
    }

    /**
     * Stop the http server.
     *
     * @return true in any case so far
     */
    @Override
    public synchronized boolean stopServices() {
        LOG.trace("--> stopServices");

        if (httpServer != null) {
            httpServer.shutdownNow();
            httpServer = null;
            LOG.info("Http server stopped");
        }
        else {
            LOG.info("Http server already stopped. No need to stop again");
        }
        running = false;

        LOG.trace("<-- stopServices");

        return true;
    }
    
    @Override
    public synchronized String getHost() {
        return host;
    }
    @Override
    public synchronized void setHost(String host) {
        this.host = host;
    }

    /**
     * Gets the base URI the REST services are published under.
     *
     * @return the base URI, e.g. http://localhost:8080/rest/
     */
    public synchronized String getBaseURI() {
        return baseURI;
    }

    @Override
    public synchronized String getPort() {
        return port;
    }
    @Override
    public synchronized void setPort(String port) {
        this.port = port;
    }
    
    @Override
    public boolean getRunning() {
        return running;
    }
}
