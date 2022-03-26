package com.home.samplerestserver.simpleclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.home.samplerestserver.messages.Airline;
import com.home.samplerestserver.messages.Credential;
import com.home.samplerestserver.messages.ServerInfo;
import com.home.samplerestserver.messages.UserInfo;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

/**
 * A simple test client just to call REST here.
 */
public class TestClient {
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
        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target(REST_MESSAGE_URL).path("simple");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.get(String.class);
        System.out.println(response);
        System.out.println(CALL_SEPARATOR);

        webTarget = client.target(REST_MESSAGE_URL).path("ping");
        invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        response = invocationBuilder.get(String.class);
        System.out.println(response);
        System.out.println(CALL_SEPARATOR);

        webTarget = client.target(REST_MESSAGE_URL).path("serverinfo");
        invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        response = invocationBuilder.get(String.class);
        System.out.println(response);
        System.out.println(CALL_SEPARATOR);

        try {
            webTarget = client.target(REST_MESSAGE_URL).path("xmlserverinfo");
            invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
            ServerInfo serverInfo = invocationBuilder.get(ServerInfo.class);
            JAXBContext jaxbContext = JAXBContext.newInstance(ServerInfo.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(serverInfo, System.out);
            System.out.println(serverInfo.getServer());
        } 
        catch (JAXBException jbex) {
            System.err.println(jbex);
        }
        System.out.println(CALL_SEPARATOR);

        try {
            webTarget = client.target(REST_MESSAGE_URL).path("credential");
            invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
            Credential credential = invocationBuilder.get(Credential.class);
            JAXBContext jaxbContext = JAXBContext.newInstance(Credential.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(credential, System.out);
            System.out.println(credential);
        }
        catch (JAXBException jbex) {
            System.err.println(jbex);
        }
        System.out.println(CALL_SEPARATOR);

        try {
            webTarget = client.target(REST_MESSAGE_URL).path("userinfo");
            invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
            UserInfo userInfo = invocationBuilder.get(UserInfo.class);
            JAXBContext jaxbContext = JAXBContext.newInstance(UserInfo.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(userInfo, System.out);
            System.out.println(userInfo);
        }
        catch (JAXBException jbex) {
            System.err.println(jbex);
        }
        System.out.println(CALL_SEPARATOR);

        Response resp = client.target("http://localhost:8080/rest/")
                .path("message")
                .path("jsonairlineinfo")
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (resp.getStatus() != 200) {
            String msg = String.format("ERROR: status: %d",
                                       resp.getStatus());
            System.err.println(msg);
        }
        else {
            Airline airline = resp.readEntity(Airline.class);

            try {
                ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                String airlineJson = mapper.writeValueAsString(airline);
                System.out.println(airlineJson);
            }
            catch (JsonProcessingException jpex) {
                System.err.println("ERROR: " + jpex);
            }

            System.out.println(airline.toString());
        }
        System.out.println(CALL_SEPARATOR);

        webTarget = client.target(REST_MESSAGE_URL);
        invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        response = invocationBuilder.options(String.class);
        System.out.println(response);
        System.out.println(CALL_SEPARATOR);
    }
}
