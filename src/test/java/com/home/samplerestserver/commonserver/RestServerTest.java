/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.samplerestserver.commonserver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 */
public class RestServerTest {

    public RestServerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

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
