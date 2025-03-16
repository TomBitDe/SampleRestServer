package com.home.samplerestserver.simpleclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.home.samplerestserver.commonserver.CustomLoggingFilter;
import com.home.samplerestserver.messages.Airline;
import com.home.samplerestserver.messages.AirlineInfo;
import com.home.samplerestserver.messages.signed.CommonResponse;
import com.home.samplerestserver.messages.signed.WeighingRequest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.client.ClientConfig;

/**
 * A simple test client just to call REST here.
 */
public class TestClient {
    private static final Logger LOG = Logger.getLogger(TestClient.class.getName());
    
    private static final String REST_MESSAGE_URL = "http://localhost:8080/rest/message/";
    private static final String CALL_SEPARATOR = "------------------------------------------------------------";

    /**
     * Starter for the client.
     * <p>
     * No args are currently evaluated.
     *
     * @param args the starter arguments
     */
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig().register(CustomLoggingFilter.class);
        Client client = ClientBuilder.newClient(clientConfig);
        
        simple(client);

        ping(client);
        
        serverInfo(client);

        jsonAirline(client);
        
        jsonAirlineInfo(client);
        
        jsonDeleteAirline(client);

        options(client);
        
        jsonWeighingRequest(client);
    }
    
    public static void simple(Client client) {
        WebTarget webTarget = client.target(REST_MESSAGE_URL).path("simple");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.get(String.class);
        LOG.info(response);
        LOG.info(CALL_SEPARATOR);
    }
    
    public static void ping(Client client) {
        WebTarget webTarget = client.target(REST_MESSAGE_URL).path("ping");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.get(String.class);
        LOG.info(response);
        LOG.info(CALL_SEPARATOR);
    }
    
    public static void serverInfo(Client client) {
        WebTarget webTarget = client.target(REST_MESSAGE_URL).path("serverinfo");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.get(String.class);
        LOG.info(response);
        LOG.info(CALL_SEPARATOR);
    }
        
    public static void jsonAirline(Client client) {
        Invocation.Builder invocationBuilder = client.target(REST_MESSAGE_URL)
                .path("jsonairline")
                .request(MediaType.APPLICATION_JSON);
        
        Airline airline = new Airline("EW", "EuroWings");
        
        Airline response = invocationBuilder.post(
                Entity.entity(airline, MediaType.APPLICATION_JSON), Airline.class);

        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            String airlineJson = mapper.writeValueAsString(response);
            LOG.info(airlineJson);
        }
        catch (JsonProcessingException jpex) {
            System.err.println("ERROR: " + jpex);
        }

        LOG.info(airline.toString());
        
        LOG.info(CALL_SEPARATOR);
    }
    
    public static void jsonAirlineInfo(Client client) {
        Response resp = client.target(REST_MESSAGE_URL)
                .path("jsonairlineinfo")
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (resp.getStatus() != 200) {
            String msg = String.format("ERROR: status: %d",
                                       resp.getStatus());
            System.err.println(msg);
        }
        else {
            AirlineInfo airlineInfo = resp.readEntity(AirlineInfo.class);

            try {
                ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                String airlineJson = mapper.writeValueAsString(airlineInfo);
                LOG.info(airlineJson);
            }
            catch (JsonProcessingException jpex) {
                System.err.println("ERROR: " + jpex);
            }

            LOG.info(airlineInfo.toString());
        }
        LOG.info(CALL_SEPARATOR);
    }
    
    public static void jsonDeleteAirline(Client client) {
        Invocation.Builder invocationBuilder = client.target(REST_MESSAGE_URL)
                .path("jsonairline")
                .path("EW")
                .request(MediaType.APPLICATION_JSON);
        
        Response response = invocationBuilder.delete();
        
        // No JSON content expected just response; NO ObjectMapper needed
        LOG.log(Level.INFO, "HTTP-Status: [{0}] Resonse=[{1}]", new Object[]{response.getStatus(), response.toString()});
        
        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()
         || response.getStatus() == Response.Status.NOT_FOUND.getStatusCode())
            LOG.log(Level.INFO, "As expected: status=[{0}] reason=[{1}]",
                    new Object[]{response.getStatus(), response.getStatusInfo()});
        else {
            LOG.log(Level.INFO, "Not expected: {0}", response.getStatus());            
        }
        LOG.info(CALL_SEPARATOR);
    }
    
    public static void options(Client client) {
        WebTarget webTarget = client.target(REST_MESSAGE_URL);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.options(String.class);
        LOG.info(response);
        LOG.info(CALL_SEPARATOR);
    }

    public static void jsonWeighingRequest(Client client) {
        Invocation.Builder invocationBuilder = client.target(REST_MESSAGE_URL)
                .path("jsonweighing")
                .request(MediaType.APPLICATION_JSON);
        
        WeighingRequest request = new WeighingRequest();
        request.getMessage().getHeader().setMsg_id("434");
        request.getMessage().setScale("SCALE1");
        
        CommonResponse response = invocationBuilder.post(
                Entity.entity(request, MediaType.APPLICATION_JSON), CommonResponse.class);

        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            String requestJson = mapper.writeValueAsString(response);
            LOG.info(requestJson);
        }
        catch (JsonProcessingException jpex) {
            System.err.println("ERROR: " + jpex);
        }

        LOG.info(response.toString());   
        LOG.info(CALL_SEPARATOR);
    }    
}
