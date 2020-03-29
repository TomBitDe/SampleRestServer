package com.home.samplerestserver.commonserver;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 */
public class TestClient {
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

        resourceTarget = target.path("message");
        request = resourceTarget.request(MediaType.TEXT_PLAIN);
        response = request.options();
        reply = response.readEntity(String.class);
        System.out.println(reply);
    }
}
