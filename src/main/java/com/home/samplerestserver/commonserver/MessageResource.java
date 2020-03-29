package com.home.samplerestserver.commonserver;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 */
@Path("message")
public class MessageResource {
    @GET
    @Path("simple")
    @Produces(MediaType.TEXT_PLAIN)
    public String message() {
        return "Yea! ";
    }

    @GET
    @Path("serverinfo")
    @Produces(MediaType.TEXT_PLAIN)
    public String serverinfo() {
        return System.getProperty("os.name") + " " + System.getProperty("os.version");
    }

    @OPTIONS
    @Produces(MediaType.TEXT_PLAIN)
    public String options() {
        return "GET";
    }
}
