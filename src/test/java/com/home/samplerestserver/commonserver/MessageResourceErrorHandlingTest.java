package com.home.samplerestserver.commonserver;

import com.home.samplerestserver.messages.signed.CommonResponse;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the error handling of MessageResource without a running server.
 */
public class MessageResourceErrorHandlingTest {
    /**
     * No need to put some code in the constructor until now.
     */
    public MessageResourceErrorHandlingTest() {
    }

    /**
     * A failing entity stream must result in HTTP 500 and an error response.
     */
    @Test
    public void testJsonWeighingRequestIOExceptionReturns500() {
        System.out.println("jsonweighing failing entity stream");

        ContainerRequestContext requestContext = createFailingRequestContext();
        MessageResource resource = new MessageResource();

        try (Response resp = resource.jsonWeighingRequest(requestContext)) {
            Assert.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    resp.getStatus());

            CommonResponse commonResponse = (CommonResponse) resp.getEntity();
            Assert.assertEquals("500", commonResponse.getStatus());
            Assert.assertNotNull(commonResponse.getError());
            Assert.assertEquals(Integer.valueOf(500), commonResponse.getError().getCode());
            Assert.assertEquals("Error reading request body",
                    commonResponse.getError().getDetail());
        }
    }

    private static ContainerRequestContext createFailingRequestContext() {
        InputStream failingStream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("simulated read failure");
            }
        };

        return (ContainerRequestContext) Proxy.newProxyInstance(
                MessageResourceErrorHandlingTest.class.getClassLoader(),
                new Class<?>[]{ContainerRequestContext.class},
                (proxy, method, args) -> {
                    if ("getEntityStream".equals(method.getName())) {
                        return failingStream;
                    }
                    throw new UnsupportedOperationException(method.getName());
                });
    }
}
