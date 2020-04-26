package com.home.samplerestserver.simpleclient;

import com.home.samplerestserver.messages.ServerInfo;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;

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
        String reply = sb2.get(String.class);
        System.out.println(reply);

        sb2 = s2.path("message").path("serverinfo").accept(MediaType.TEXT_PLAIN);
        reply = sb2.get(String.class);
        System.out.println(reply);

        sb2 = s2.path("message").path("xmlserverinfo").accept(MediaType.APPLICATION_XML);
        System.out.println(sb2.get(ServerInfo.class).getServer());

        sb2 = s2.path("message").path("xmlserverinfo").accept(MediaType.TEXT_PLAIN);
        reply = sb2.get(String.class);
        System.out.println(reply);
    }
}
