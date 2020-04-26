package com.home.samplerestserver.commonserver;

import com.home.samplerestserver.messages.ServerInfo;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for MessageResource
 */
public class MessageResourceTest {
    WebTarget target;

    /**
     * No need to put some code in the constructor until now.
     */
    public MessageResourceTest() {
    }

    /**
     * No setup needed util now.
     */
    @BeforeClass
    public static void setUpClass() {
        MainEntry.startWS();
    }

    /**
     * No tear down needed until now.
     */
    @AfterClass
    public static void tearDownClass() {
        MainEntry.stopWS();
    }

    /**
     * Method test setup creates a new REST client for each test.
     */
    @Before
    public void setUp() {
        Client client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/rest");
    }

    /**
     * No method test tesr down needed until now.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of message method, of class MessageResource.
     */
    @Test
    public void testMessage() {
        System.out.println("message");

        WebResource s2 = com.sun.jersey.api.client.Client.create().resource("http://localhost:8080/rest");
        Builder sb2 = s2.path("message").path("simple").accept(MediaType.TEXT_PLAIN);
        String reply = sb2.get(String.class);

        Assert.assertEquals("Yea! ", reply);
    }

    /**
     * Test of serverinfo method, of class MessageResource.
     */
    @Test
    public void testServerinfo() {
        System.out.println("serverinfo");

        WebResource s2 = com.sun.jersey.api.client.Client.create().resource("http://localhost:8080/rest");
        Builder sb2 = s2.path("message").path("serverinfo").accept(MediaType.TEXT_PLAIN);
        String reply = sb2.get(String.class);

        Assert.assertEquals("Windows 8.1 6.3", reply);
    }

    /**
     * Test of xmlserverinfo method, of class MessageResource.
     */
    @Test
    public void testXmlserverinfo() {
        System.out.println("xmlserverinfo");

        WebResource s2 = com.sun.jersey.api.client.Client.create().resource("http://localhost:8080/rest");
        Builder sb2 = s2.path("message").path("xmlserverinfo").accept(MediaType.APPLICATION_XML);
        String reply = sb2.get(ServerInfo.class).getServer();

        Assert.assertEquals("Windows 8.1 6.3", reply);
    }

    /**
     * Test of options method, of class MessageResource.
     */
    @Test
    public void testOptions() {
        System.out.println("options");

        WebResource s2 = com.sun.jersey.api.client.Client.create().resource("http://localhost:8080/rest");
        Builder sb2 = s2.path("message").accept(MediaType.TEXT_PLAIN);
        String reply = sb2.get(String.class);

        Assert.assertEquals("GET", reply);
    }

}
