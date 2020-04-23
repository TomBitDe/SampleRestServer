package com.home.samplerestserver.commonserver;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 * Simple services.
 */
@Path("message")
public class MessageResource {
    private static final Logger LOG = Logger.getLogger(MessageResource.class.getName());

    /**
     * Produce a simple message string.
     *
     * @return the "Yea! "
     */
    @GET
    @Path("simple")
    @Produces(MediaType.TEXT_PLAIN)
    public String message() {
        LOG.debug("Enter message");
        return "Yea! ";
    }

    /**
     * Produce a simple message containing server information.
     *
     * @return the server information
     */
    @GET
    @Path("serverinfo")
    @Produces(MediaType.TEXT_PLAIN)
    public String serverinfo() {
        LOG.debug("Enter serverinfo");
        return System.getProperty("os.name") + " " + System.getProperty("os.version");
    }

    /**
     * Produce a simple message containing server information as XML.
     *
     * @return the server information
     */
    @GET
    @Path("xmlserverinfo")
    @Produces(MediaType.APPLICATION_XML)
    public ServerInfo xmlserverinfo() {
        LOG.debug("Enter xmlserverinfo");

        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setServer(System.getProperty("os.name") + " " + System.getProperty("os.version"));

        return serverInfo;
    }

    /**
     * Show the implemented options.
     *
     * @return the options
     */
    @OPTIONS
    @Produces(MediaType.TEXT_PLAIN)
    public String options() {
        LOG.debug("Enter options");
        return "GET";
    }
}
