package com.home.samplerestserver.commonserver;

import com.home.samplerestserver.messages.ServerInfo;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
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
    Client client;
    WebTarget webTarget;

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
        client = javax.ws.rs.client.ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
        webTarget = client.target("http://localhost:8080/rest/");
    }

    /**
     * No method test tesr down needed until now.
     */
    @After
    public void tearDown() {
        webTarget = null;
        client = null;
    }

    /**
     * Test of message method, of class MessageResource.
     */
    @Test
    public void testMessage() {
        System.out.println("message");

        webTarget = webTarget.path("message").path("simple");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.get(String.class);

        Assert.assertEquals("Yea! ", response);
    }

    /**
     * Test of serverinfo method, of class MessageResource.
     */
    @Test
    public void testServerinfo() {
        System.out.println("serverinfo");

        webTarget = webTarget.path("message").path("serverinfo");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.get(String.class);

        Assert.assertEquals("Windows 8.1 6.3", response);
    }

    /**
     * Test of xmlserverinfo method, of class MessageResource.
     */
    @Test
    public void testXmlserverinfo() {
        System.out.println("xmlserverinfo");

        webTarget = webTarget.path("message").path("xmlserverinfo");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        String response = invocationBuilder.get(ServerInfo.class).getServer();

        Assert.assertEquals("Windows 8.1 6.3", response);
    }

    /**
     * Test of options method, of class MessageResource.
     */
    @Test
    public void testOptions() {
        System.out.println("options");

        webTarget = webTarget.path("message");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.options(String.class);

        Assert.assertEquals("GET", response);
    }

}
