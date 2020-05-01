package com.home.samplerestserver.commonserver;

import com.home.samplerestserver.messages.Credential;
import com.home.samplerestserver.messages.ServerInfo;
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
        final String ret = "Yea! ";
        LOG.debug("Return [" + ret + ']');

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
        final String ret = System.getProperty("os.name") + " " + System.getProperty("os.version");
        LOG.debug("Return [" + ret + ']');

        return ret;
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
        ServerInfo serverInfo = new ServerInfo();

        serverInfo.setServer(System.getProperty("os.name") + " " + System.getProperty("os.version"));
        LOG.debug("Return [" + serverInfo.getServer() + ']');

        return serverInfo;
    }

    /**
     * Produce a Credential message containing two elements as XML.
     *
     * @return the credential informations
     */
    @GET
    @Path("credential")
    @Produces(MediaType.APPLICATION_XML)
    public Credential credential() {
        Credential val = new Credential("Dummy01", 1234);

        LOG.debug(val);

        return val;
    }

    /**
     * Show the implemented options.
     *
     * @return the options
     */
    @OPTIONS
    @Produces(MediaType.TEXT_PLAIN)
    public String options() {
        final String ret = "GET";
        LOG.debug("Return [" + ret + ']');

        return ret;
    }
}
