package com.home.samplerestserver.commonserver;

import com.home.samplerestserver.messages.Airline;
import com.home.samplerestserver.messages.Credential;
import com.home.samplerestserver.messages.ServerInfo;
import com.home.samplerestserver.messages.UserInfo;
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
     * Test of ping method, of class MessageResource.
     */
    @Test
    public void testPing() {
        System.out.println("ping");

        webTarget = webTarget.path("message").path("ping");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
        String response = invocationBuilder.get(String.class);

        Assert.assertTrue(response.startsWith("Pong on "));
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
     * Test of xmlserverinfo method, of class MessageResource.
     */
    @Test
    public void testXmlserverinfo() {
        System.out.println("xmlserverinfo");

        webTarget = webTarget.path("message").path("xmlserverinfo");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        String response = invocationBuilder.get(ServerInfo.class).getServer();

        Assert.assertNotNull(response);
    }

    /**
     * Test of credential method, of class MessageResource.
     */
    @Test
    public void testCredential() {
        System.out.println("credential");

        webTarget = webTarget.path("message").path("credential");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Credential response = invocationBuilder.get(Credential.class);

        Assert.assertEquals(new Credential("Dummy01", 1234), response);
    }

    /**
     * Test of userinfo method, of class MessageResource.
     */
    @Test
    public void testUserInfo() {
        System.out.println("userinfo");

        webTarget = webTarget.path("message").path("userinfo");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        UserInfo response = invocationBuilder.get(UserInfo.class);

        Assert.assertEquals(new UserInfo("Hans", new Credential("Dummy01", 1234)), response);
    }

    /**
     * Test of airlineinfo method, of class MessageResource.
     */
    @Test
    public void testJsonAirlineInfo() {
        System.out.println("jsonairlineinfo");

        webTarget = webTarget.path("message").path("jsonairlineinfo");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Airline response = invocationBuilder.get(Airline.class);

        Assert.assertEquals(new Airline("EW", "EuroWings"), response);
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
