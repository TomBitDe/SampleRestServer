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

    @GET
    @Path("simple")
    @Produces(MediaType.TEXT_PLAIN)
    public String message() {
        LOG.debug("Enter message");
        return "Yea! ";
    }

    @GET
    @Path("serverinfo")
    @Produces(MediaType.TEXT_PLAIN)
    public String serverinfo() {
        LOG.debug("Enter serverinfo");
        return System.getProperty("os.name") + " " + System.getProperty("os.version");
    }

    @OPTIONS
    @Produces(MediaType.TEXT_PLAIN)
    public String options() {
        LOG.debug("Enter options");
        return "GET";
    }
}
