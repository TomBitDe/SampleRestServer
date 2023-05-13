package com.home.samplerestserver.commonserver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests for MainEntry.
 */
public class MainEntryTest {

    /**
     * No need to put some code in the constructor until now.
     */
    public MainEntryTest() {
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
     * No method test tear down needed until now.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of startWS method, of class MainEntry.
     */
    @Test
    public void testStartWS() {
        System.out.println("startWS");
        boolean result = MainEntry.startWS();
        Assert.assertTrue(result);
        result = MainEntry.startWS();
        Assert.assertTrue(result);
    }

    /**
     * Test of stopWS method, of class MainEntry.
     */
    @Test
    public void testStopWS() {
        System.out.println("stopWS");
        boolean result = MainEntry.stopWS();
        Assert.assertTrue(result);
        result = MainEntry.stopWS();
        Assert.assertTrue(result);
    }

    /**
     * Test of main method, of class MainEntry.
     * 
     * @throws Exception in case of any exception
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        MainEntry.main(args);
        Assert.assertTrue(MainEntry.stopWS());
    }
}
