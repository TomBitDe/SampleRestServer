package com.home.samplerestserver.commonserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.samplerestserver.messages.Airline;
import com.home.samplerestserver.messages.AirlineInfo;
import com.home.samplerestserver.messages.signed.CommonResponse;
import com.home.samplerestserver.messages.signed.WeighingRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple services.
 */
@Path("message")
public class MessageResource {
    private static final Logger LOG = LogManager.getLogger(MessageResource.class.getName());
    private static final List<Airline> memList = new ArrayList<>();
    
    @Context
    private ContainerRequestContext requestContext;
    
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
     * Produce a Response message containing a simple class as JSON.
     *
     * @param code the airline code
     * @return common response
     */
    @DELETE
    @Path("jsonairline/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response jsonDeleteAirlineInfo(@PathParam("code") String code) {
        if (code == null || code.isEmpty()) {
            LOG.error("code=null or empty");
            return  Response.status(Response.Status.NOT_ACCEPTABLE).build();
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

    @POST
    @Path("jsonweighing")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CommonResponse jsonWeighingRequest(@Context ContainerRequestContext requestContext) {
        CommonResponse ret = new CommonResponse();
        
        try {
            InputStream inputStream = requestContext.getEntityStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            StringBuilder rawData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                rawData.append(line);
            }

            LOG.info("Raw Data: " + rawData.toString());

            // JSON in WeighingRequest umwandeln
            ObjectMapper objectMapper = new ObjectMapper();
            WeighingRequest weighingRequest = objectMapper.readValue(rawData.toString(),
                    WeighingRequest.class);

            // WeighingRequest-Object is now available
            LOG.info("Parsed WeighingRequest: " + weighingRequest);

            // Beispiel: Daten verarbeiten
            ret.setStatus("200");
            ret.setMessage("Weighing request received successfully");
        }
        catch (IOException e) {
            LOG.error("Error reading request body", e);
        }

        return ret;
    }
   
//    @POST
//    @Path("jsonweighing")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String jsonWeighingRequest() {
//        if (request == null) {
//            return "HttpServletRequest is NULL!";
//        }
//
//        return "Request received from: " + request.getMediaType();
//    }
}
