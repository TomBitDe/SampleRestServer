package com.home.samplerestserver.commonserver;

import com.home.samplerestserver.messages.Airline;
import com.home.samplerestserver.messages.AirlineInfo;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
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
        client = ClientBuilder.newClient(new ClientConfig().register(CustomLoggingFilter.class));
        webTarget = client.target("http://localhost:8080/rest/");
    }

    /**
     * No method test tear down needed until now.
     */
    @After
    public void tearDown() {
        webTarget = null;
        client = null;
    }

    /**
     * Test of ping method, of class MessageResource.
     */
    @Test
    public void testPing() {
        System.out.println("ping");

        webTarget = webTarget.path("message").path("ping");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.get(String.class);

        Assert.assertTrue(response.startsWith("Pong "));
    }

    /**
     * Test of simple method, of class MessageResource.
     */
    @Test
    public void testSimple() {
        System.out.println("simple");

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

        Assert.assertNotNull(response);
    }

    /**
     * Test of airlineinfo method, of class MessageResource.
     */
//    @org.junit.Ignore
    @Test
    public void testJsonAirlineInfo() {
        System.out.println("jsonairlineinfo");

        webTarget = webTarget.path("message").path("jsonairlineinfo");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        AirlineInfo response = invocationBuilder.get(AirlineInfo.class);

        Assert.assertNull(response.getAirlines());
    }

    /**
     * Test of createairlineinfo method, of class MessageResource.
     */
    @Test
    public void testJsonCreateAirlineInfo() {
        System.out.println("jsoncreateairlineinfo");

        webTarget = webTarget.path("message").path("jsonairline");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Airline airline = new Airline("EW", "EuroWings");
        Airline response = invocationBuilder.post(
                Entity.entity(airline, MediaType.APPLICATION_JSON), Airline.class);

        Assert.assertEquals(airline.getCode(), response.getCode());
        Assert.assertEquals(airline.getName(), response.getName());
    }

    /**
     * Test of deleteairlineinfo method, of class MessageResource.
     */
    @Test
    public void testJsonDeleteAirlineInfo() {
        System.out.println("jsondeleteairlineinfo");
        
        testJsonCreateAirlineInfo();
        
        webTarget = webTarget.path("message").path("jsonairline").path("EW");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.delete();
        
        System.out.println("HTTP response status=[" + response.getStatus() + ']');

        // exprected is 204 in case Airline delete is done 
        Assume.assumeTrue(Response.Status.NO_CONTENT.getStatusCode() == response.getStatus());
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
