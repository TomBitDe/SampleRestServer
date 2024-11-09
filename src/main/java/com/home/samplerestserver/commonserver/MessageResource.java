package com.home.samplerestserver.commonserver;

import com.home.samplerestserver.messages.Airline;
import com.home.samplerestserver.messages.AirlineInfo;
import com.home.samplerestserver.messages.Credential;
import com.home.samplerestserver.messages.ServerInfo;
import com.home.samplerestserver.messages.UserInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple services.
 */
@Path("message")
public class MessageResource {
    private static final Logger LOG = LogManager.getLogger(MessageResource.class.getName());
    private static final List<Airline> memList = new ArrayList<>();
    
    /**
     * Produce a pong message string.
     *
     * @return the pong text
     */
    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        LOG.debug("RESTful Service 'message' is running ==> ping");
        
        String hostname = System.getenv("HOSTNAME");
        if (hostname == null) {
            hostname = System.getenv("COMPUTERNAME"); // Fallback for Windows
        }
 
        return "Pong from " + hostname + " at " + new Date().toString();
    }

    /**
     * Produce a simple message string.
     *
     * @return the "Yea! "
     */
    @GET
    @Path("simple")
    @Produces(MediaType.TEXT_PLAIN)
    public String simple() {
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
     * Produce a UserInfo message containing a complex class as XML.
     *
     * @return the user informations
     */
    @GET
    @Path("userinfo")
    @Produces(MediaType.APPLICATION_XML)
    public UserInfo userinfo() {
        UserInfo val = new UserInfo("Hans", new Credential("Dummy01", 1234));

        LOG.debug(val);

        return val;
    }

    /**
     * Produce a AirlineInfo message containing a complex class as JSON.
     *
     * @return the airline informations
     */
    @GET
    @Path("jsonairlineinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public AirlineInfo jsonairlineinfo() {
        AirlineInfo ret = new AirlineInfo(new ArrayList<>(memList));
        
        LOG.debug(ret);
        
        return ret;
    }

    /**
     * Produce a Airline message containing a simple class as JSON.
     *
     * @param airline the airline data
     * @return the airline informations
     */
    @POST
    @Path("jsonairline")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Airline jsonCreateAirlineInfo(Airline airline) {
        Airline ret = new Airline();
        
        if (airline == null) {
            LOG.error("airline=null");
        }
        else {
            ret.setCode(airline.getCode());
            ret.setName(airline.getName());
            
            LOG.debug(ret);

            memList.add(ret);
        }
        
        return ret;
    }

    /**
     * Produce a Airline message containing a simple class as JSON.
     *
     * @param code the airline code
     * @return common response
     */
    @DELETE
    @Path("jsonairline/{code}")
    public Response jsonDeleteAirlineInfo(@PathParam("code") String code) {
        if (code == null || code.isEmpty()) {
            LOG.error("code=null or empty");
            return  Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            Airline ret = memList.stream()
                    .filter(a -> a.getCode().equals(code))
                    .findFirst()
                    .orElse(null);

            if (ret != null) {
                memList.remove(ret);
                LOG.debug("Deleted airline: " + ret);
                return Response.status(Response.Status.NO_CONTENT).build(); // 204 No Content
            }
            else {
                LOG.warn("Airline not found with code: " + code);
                return Response.status(Response.Status.NOT_FOUND).build(); // 404 No Found
            }
        }
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
