package com.home.samplerestserver.commonserver;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
@Provider
public class CustomLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private static final Logger LOGGER = LogManager.getLogger(CustomLoggingFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Log request information
        LOGGER.info("Incoming Request: {} {}", requestContext.getMethod(), requestContext.getUriInfo().getRequestUri());
        LOGGER.info("Headers: {}", requestContext.getHeaders());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // Log resonse information
        LOGGER.info("Outgoing Response: {}", responseContext.getStatus());
        LOGGER.info("Headers: {}", responseContext.getHeaders());
    }
}
