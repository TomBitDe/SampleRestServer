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
 *
 * @author Tom
 */
public class MainEntryTest {

    public MainEntryTest() {
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
     * Test of startWS method, of class MainEntry.
     */
    @Test
    public void testStartWS() {
        System.out.println("startWS");
        boolean result = MainEntry.startWS();
        Assert.assertEquals(true, result);
    }

    /**
     * Test of stopWS method, of class MainEntry.
     */
    @Test
    public void testStopWS() {
        System.out.println("stopWS");
        boolean result = MainEntry.stopWS();
        Assert.assertEquals(true, result);
    }

    /**
     * Test of main method, of class MainEntry.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        MainEntry.main(args);
        Assert.assertEquals(true, MainEntry.stopWS());
    }
}
