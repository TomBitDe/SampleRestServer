package com.home.samplerestserver.commonserver;

import java.lang.Thread.UncaughtExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Special handler for uncaught exceptions.
 */
public class UncaughtExceptionHandlerImpl implements UncaughtExceptionHandler {
    private static final Logger LOG = LogManager.getLogger(UncaughtExceptionHandlerImpl.class.getName());

    /**
     * Log the error message for the uncaught exception.
     *
     * @param t the thread that raised the exception
     * @param e the error itself
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LOG.fatal("Thread " + t.getClass().getName() + " " + e.getMessage(), e);
        System.exit(1);
    }
}
