package com.home.samplerestserver.commonserver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.accesslog.AccessLogBuilder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Use the Grizzly http server to handle REST calls.
 */
public class RestServer implements RestServerMBean {
    private static final Logger LOG = LogManager.getLogger(RestServer.class.getName());

    private static final String HOST_DEFAULT = "localhost";
    private static final String PORT_DEFAULT = "8080";
    private static final String PROTOCOL;
    private static final String PATH;
    private static String baseURI;
    private static String host;
    private static String port;
    
    private static boolean running;

    static {
        PROTOCOL = "http://";
        PATH = "rest";
        running = false;
    }

    private static HttpServer httpServer = null;

    /**
     * It's not allowed to create an instance.
     */
    public RestServer() {
        LOG.trace("--> RestServer");
        host = System.getenv("HOSTNAME");
        if (host == null) host = HOST_DEFAULT;
        
        port = System.getenv("PORT");
        if (port == null) port = PORT_DEFAULT;
        
        baseURI = PROTOCOL 
                + host + ":" + port + "/" + PATH + "/";
        LOG.info("baseURI = {}", baseURI);
        
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
    public boolean startServices() {
        LOG.trace("--> startServices");

        try {
            running = false;
            
            if (httpServer == null) {
                baseURI = PROTOCOL + getHost() + ":" + getPort() + "/" + PATH + "/";
                
                ResourceConfig rc = new ResourceConfig()
                        .packages("com.home.samplerestserver.commonserver")
                        .property("jersey.config.server.tracing.type ", "ALL");
                httpServer = GrizzlyHttpServerFactory
                        .createHttpServer(new URI(baseURI), rc);
                ServerConfiguration serverConfiguration = httpServer.getServerConfiguration();

                final AccessLogBuilder builder = new AccessLogBuilder("./logs/access.log");
                builder.instrument(serverConfiguration);

                httpServer.start();
                LOG.info("Jersey app started with WADL available at " + baseURI + "application.wadl");
            }
            else {
                LOG.info("Services already running. No need to start again");
            }
            
            running = true;
        }
        catch (URISyntaxException | IOException ex) {
            LOG.error("StartServices failed: " + ex.getMessage());
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
    public boolean stopServices() {
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
    public String getHost() {
        return host;
    }
    @Override
    public void setHost(String host) {
        RestServer.host = host;
    }

    @Override
    public String getPort() {
        return port;
    }
    @Override
    public void setPort(String port) {
        RestServer.port = port;
    }
    
    @Override
    public boolean getRunning() {
        return running;
    }
}
