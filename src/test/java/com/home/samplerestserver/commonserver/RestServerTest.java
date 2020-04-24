package com.home.samplerestserver.commonserver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests for RestServer.
 */
public class RestServerTest {

    /**
     * No need to put some code in the constructor until now.
     */
    public RestServerTest() {
    }

    /**
     * No setup needed util now.
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * No tear down needed until now.
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * No method test setup needed until now.
     */
    @Before
    public void setUp() {
    }

    /**
     * No method test tesr down needed until now.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of startServices method, of class RestServer.
     */
    @Test
    public void testStartServices() {
        System.out.println("startServices");
        boolean result = RestServer.startServices();
        Assert.assertEquals(true, result);
    }

    /**
     * Test of stopServices method, of class RestServer.
     */
    @Test
    public void testStopServices() {
        System.out.println("stopServices");
        boolean result = RestServer.stopServices();
        Assert.assertEquals(true, result);
    }
}
