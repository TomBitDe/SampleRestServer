package com.home.samplerestserver.commonserver;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
@Provider
public class CustomLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private static final Logger LOGGER = Logger.getLogger(CustomLoggingFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Log request information
        LOGGER.log(Level.INFO, "Incoming Request: {0} {1}", 
                   new Object[]{requestContext.getMethod(), requestContext.getUriInfo().getRequestUri()});
        LOGGER.log(Level.INFO, "Headers: {0}", requestContext.getHeaders());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // Log resonse information
        LOGGER.log(Level.INFO, "Outgoing Response: {0}", responseContext.getStatus());
        LOGGER.log(Level.INFO, "Headers: {0}", responseContext.getHeaders());
    }
}
