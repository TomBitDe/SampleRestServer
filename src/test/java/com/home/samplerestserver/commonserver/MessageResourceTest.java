package com.home.samplerestserver.commonserver;

import com.home.samplerestserver.messages.Airline;
import com.home.samplerestserver.messages.AirlineInfo;
import com.home.samplerestserver.messages.signed.CommonResponse;
import com.home.samplerestserver.messages.signed.WeighingRequest;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
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
        webTarget = client.target(MainEntry.getRestServer().getBaseURI());
    }

    /**
     * No method test tear down needed until now.
     */
    @After
    public void tearDown() {
        webTarget = null;
        if (client != null) {
            client.close();
            client = null;
        }
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
    @Test
    public void testJsonAirlineInfo() {
        System.out.println("jsonairlineinfo GET");

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
        System.out.println("jsoncreateairlineinfo POST");

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
        System.out.println("jsondeleteairlineinfo DELETE");
        
        webTarget = webTarget.path("message").path("jsonairline").path("EW");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response resp = invocationBuilder.delete();
        
        System.out.println("HTTP response status=[" + resp.getStatus() + ']');

        // exprected is 204 in case Airline delete is done OR 404 in case Airline not found
        assertTrue(Response.Status.NO_CONTENT.getStatusCode() == resp.getStatus()
        || Response.Status.NOT_FOUND.getStatusCode() == resp.getStatus());
    }
    
    /**
     * Test of jsonWeighingRequest method with a valid request body.
     */
    @Test
    public void testJsonWeighingRequestValid() {
        System.out.println("jsonweighing valid POST");

        webTarget = webTarget.path("message").path("jsonweighing");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        WeighingRequest request = new WeighingRequest();
        request.getMessage().getHeader().setMsg_id("434");
        request.getMessage().setScale("SCALE1");

        try (Response resp = invocationBuilder.post(
                Entity.entity(request, MediaType.APPLICATION_JSON))) {
            System.out.println("HTTP response status=[" + resp.getStatus() + ']');
            Assert.assertEquals(Response.Status.OK.getStatusCode(), resp.getStatus());

            CommonResponse commonResponse = resp.readEntity(CommonResponse.class);
            Assert.assertEquals("200", commonResponse.getStatus());
            Assert.assertEquals("Weighing request received successfully",
                    commonResponse.getMessage());
        }
    }

    /**
     * Test of jsonWeighingRequest method with malformed JSON.
     */
    @Test
    public void testJsonWeighingRequestMalformedJson() {
        System.out.println("jsonweighing malformed JSON POST");

        webTarget = webTarget.path("message").path("jsonweighing");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        try (Response resp = invocationBuilder.post(
                Entity.entity("{not valid json", MediaType.APPLICATION_JSON))) {
            System.out.println("HTTP response status=[" + resp.getStatus() + ']');
            Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), resp.getStatus());

            CommonResponse commonResponse = resp.readEntity(CommonResponse.class);
            Assert.assertEquals("400", commonResponse.getStatus());
            Assert.assertNotNull(commonResponse.getError());
            Assert.assertEquals(Integer.valueOf(400), commonResponse.getError().getCode());
            Assert.assertEquals("Invalid JSON request body",
                    commonResponse.getError().getDetail());
        }
    }

    /**
     * Test of jsonWeighingRequest method with an empty request body.
     */
    @Test
    public void testJsonWeighingRequestEmptyBody() {
        System.out.println("jsonweighing empty body POST");

        webTarget = webTarget.path("message").path("jsonweighing");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        try (Response resp = invocationBuilder.post(
                Entity.entity("", MediaType.APPLICATION_JSON))) {
            System.out.println("HTTP response status=[" + resp.getStatus() + ']');
            Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), resp.getStatus());
        }
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

    /**
     * Test that deleting an unknown airline returns 404.
     */
    @Test
    public void testJsonDeleteUnknownAirlineReturns404() {
        System.out.println("jsondeleteairlineinfo unknown code DELETE");

        webTarget = webTarget.path("message").path("jsonairline").path("NOEXIST");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        try (Response resp = invocationBuilder.delete()) {
            System.out.println("HTTP response status=[" + resp.getStatus() + ']');
            Assert.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), resp.getStatus());
        }
    }

    /**
     * Test that posting the same airline twice keeps a single entry.
     */
    @Test
    public void testJsonCreateDuplicateAirlineInfo() {
        System.out.println("jsoncreateairlineinfo duplicate POST");

        final String code = "DUP";
        Airline airline = new Airline(code, "Duplicate Airline");

        try {
            WebTarget createTarget = webTarget.path("message").path("jsonairline");

            try (Response first = createTarget.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(airline, MediaType.APPLICATION_JSON))) {
                Assert.assertEquals(Response.Status.OK.getStatusCode(), first.getStatus());
            }

            try (Response second = createTarget.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(airline, MediaType.APPLICATION_JSON))) {
                System.out.println("HTTP response status=[" + second.getStatus() + ']');
                Assert.assertEquals(Response.Status.OK.getStatusCode(), second.getStatus());

                Airline responseAirline = second.readEntity(Airline.class);
                Assert.assertEquals(code, responseAirline.getCode());
                Assert.assertEquals(airline.getName(), responseAirline.getName());
            }

            AirlineInfo airlineInfo = webTarget.path("message").path("jsonairlineinfo")
                    .request(MediaType.APPLICATION_JSON)
                    .get(AirlineInfo.class);

            long occurrences = 0;
            if (airlineInfo.getAirlines() != null) {
                occurrences = airlineInfo.getAirlines().stream()
                        .filter(a -> code.equals(a.getCode()))
                        .count();
            }

            Assert.assertEquals("Airline " + code + " must be stored exactly once", 1L, occurrences);
        }
        finally {
            webTarget.path("message").path("jsonairline").path(code)
                    .request(MediaType.APPLICATION_JSON)
                    .delete()
                    .close();
        }
    }

    /**
     * Test simultaneous POST, DELETE and GET requests.
     *
     * @throws Exception if a worker fails or times out
     */
    @Test
    public void testConcurrentPostAndDelete() throws Exception {
        System.out.println("jsonairline concurrent POST/DELETE/GET");

        final int writers = 8;
        final int readers = 8;
        final int iterations = 5;
        final String prefix = "CONC";

        ExecutorService executor = Executors.newFixedThreadPool(writers + readers);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<?>> futures = new ArrayList<>();

        try {
            for (int writer = 0; writer < writers; writer++) {
                final int writerIndex = writer;
                futures.add(executor.submit(() -> {
                    start.await();
                    for (int i = 0; i < iterations; i++) {
                        String code = prefix + writerIndex + "_" + i;
                        Airline airline = new Airline(code, "Concurrent " + code);

                        try (Response postResp = webTarget.path("message").path("jsonairline")
                                .request(MediaType.APPLICATION_JSON)
                                .post(Entity.entity(airline, MediaType.APPLICATION_JSON))) {
                            Assert.assertEquals(Response.Status.OK.getStatusCode(),
                                    postResp.getStatus());
                        }

                        try (Response deleteResp = webTarget.path("message")
                                .path("jsonairline").path(code)
                                .request(MediaType.APPLICATION_JSON)
                                .delete()) {
                            Assert.assertEquals(Response.Status.NO_CONTENT.getStatusCode(),
                                    deleteResp.getStatus());
                        }
                    }
                    return null;
                }));
            }

            for (int reader = 0; reader < readers; reader++) {
                futures.add(executor.submit(() -> {
                    start.await();
                    for (int i = 0; i < iterations; i++) {
                        try (Response getResp = webTarget.path("message").path("jsonairlineinfo")
                                .request(MediaType.APPLICATION_JSON)
                                .get()) {
                            Assert.assertEquals(Response.Status.OK.getStatusCode(),
                                    getResp.getStatus());
                        }
                    }
                    return null;
                }));
            }

            start.countDown();

            for (Future<?> future : futures) {
                future.get(60, TimeUnit.SECONDS);
            }
        }
        finally {
            executor.shutdownNow();

            for (int writer = 0; writer < writers; writer++) {
                for (int i = 0; i < iterations; i++) {
                    String code = prefix + writer + "_" + i;
                    webTarget.path("message").path("jsonairline").path(code)
                            .request(MediaType.APPLICATION_JSON)
                            .delete()
                            .close();
                }
            }
        }

        AirlineInfo airlineInfo = webTarget.path("message").path("jsonairlineinfo")
                .request(MediaType.APPLICATION_JSON)
                .get(AirlineInfo.class);

        if (airlineInfo.getAirlines() != null) {
            for (Airline airline : airlineInfo.getAirlines()) {
                Assert.assertFalse("Unexpected leftover airline: " + airline,
                        airline.getCode().startsWith(prefix));
            }
        }
    }

}
