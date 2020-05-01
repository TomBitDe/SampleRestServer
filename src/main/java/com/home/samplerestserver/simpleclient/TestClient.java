package com.home.samplerestserver.simpleclient;

import com.home.samplerestserver.messages.Credential;
import com.home.samplerestserver.messages.ServerInfo;
import com.home.samplerestserver.messages.UserInfo;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

/**
 * A simple test client just to call REST here.
 */
public class TestClient {
    /**
     * Starter for the client.
     * <p>
     * No args are currently evaluated.
     *
     * @param args the starter arguments
     */
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        WebTarget webTarget = client.target("http://localhost:8080/rest/").path("message").path("simple");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.get(String.class);
        System.out.println(response);

        webTarget = client.target("http://localhost:8080/rest/").path("message").path("serverinfo");
        invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        response = invocationBuilder.get(String.class);
        System.out.println(response);

        webTarget = client.target("http://localhost:8080/rest/").path("message").path("xmlserverinfo");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.get(ServerInfo.class).getServer();
        System.out.println(response);

        webTarget = client.target("http://localhost:8080/rest/").path("message").path("credential");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Credential credential = invocationBuilder.get(Credential.class);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Credential.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(credential, System.out);
        }
        catch (JAXBException jbex) {
            System.err.println(jbex);
        }

        webTarget = client.target("http://localhost:8080/rest/").path("message").path("userinfo");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        UserInfo userInfo = invocationBuilder.get(UserInfo.class);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(UserInfo.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(userInfo, System.out);
        }
        catch (JAXBException jbex) {
            System.err.println(jbex);
        }

        webTarget = client.target("http://localhost:8080/rest/").path("message");
        invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        response = invocationBuilder.options(String.class);
        System.out.println(response);
    }
}
