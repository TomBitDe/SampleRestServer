package com.home.samplerestserver.simpleclient;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.StatusType;

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
        WebResource s2 = com.sun.jersey.api.client.Client.create().resource("http://localhost:8080/rest");
        WebResource.Builder sb2 = s2.path("message").path("simple").accept(MediaType.TEXT_PLAIN);
        ClientResponse response = sb2.get(ClientResponse.class);
        StatusType statusInfo = response.getStatusInfo();
        System.out.println("StatusCode=[" + statusInfo.getStatusCode() + "} Reason=[" + statusInfo.getReasonPhrase() + ']');
        System.out.println(response.getEntity(String.class));

        sb2 = s2.path("message").path("serverinfo").accept(MediaType.TEXT_PLAIN);
        response = sb2.get(ClientResponse.class);
        System.out.println(response.getEntity(String.class));

//        sb2 = s2.path("message").path("xmlserverinfo").accept(MediaType.APPLICATION_XML);
//        System.out.println(sb2.get(ServerInfo.class).getServer());
//
        sb2 = s2.path("message").path("xmlserverinfo").accept(MediaType.TEXT_PLAIN);
        response = sb2.get(ClientResponse.class);
        System.out.println(response.getEntity(String.class));
    }
}
