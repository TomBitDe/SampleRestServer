package com.home.samplerestserver.simpleclient;

import com.home.samplerestserver.messages.ServerInfo;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/rest");

        WebTarget resourceTarget = target.path("message/simple");
        Invocation.Builder request = resourceTarget.request(MediaType.TEXT_PLAIN);
        Response response = request.get();
        String reply = response.readEntity(String.class);
        System.out.println(reply);

        resourceTarget = target.path("message/serverinfo");
        request = resourceTarget.request(MediaType.TEXT_PLAIN);
        response = request.get();
        reply = response.readEntity(String.class);
        System.out.println(reply);

        WebResource s2 = com.sun.jersey.api.client.Client.create().resource("http://localhost:8080/rest");
        WebResource.Builder sb2 = s2.path("message").path("xmlserverinfo").accept(MediaType.APPLICATION_XML);
        System.out.println(sb2.get(ServerInfo.class).getServer());

        resourceTarget = target.path("message");
        request = resourceTarget.request(MediaType.TEXT_PLAIN);
        response = request.options();
        reply = response.readEntity(String.class);
        System.out.println(reply);
    }
}
