package com.home.samplerestserver.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the signature strategy factory.
 */
public class SignatureAlgorithmsTest {
    /**
     * No need to put some code in the constructor until now.
     */
    public SignatureAlgorithmsTest() {
    }

    /**
     * MD5 must resolve to the legacy implementation.
     */
    @Test
    public void testOfMd5() {
        Assert.assertTrue(SignatureAlgorithms.of("MD5") instanceof MD5Signature);
        Assert.assertTrue(SignatureAlgorithms.of("md5") instanceof MD5Signature);
    }

    /**
     * HMAC-SHA256 (in several spellings) must resolve to the HMAC implementation.
     */
    @Test
    public void testOfHmacSha256() {
        Assert.assertTrue(SignatureAlgorithms.of("HmacSHA256") instanceof HmacSha256Signature);
        Assert.assertTrue(SignatureAlgorithms.of("HMAC-SHA256") instanceof HmacSha256Signature);
        Assert.assertTrue(SignatureAlgorithms.of("hmac_sha256") instanceof HmacSha256Signature);
    }

    /**
     * Unknown or missing signature types must be rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOfUnknown() {
        SignatureAlgorithms.of("SHA1");
    }

    /**
     * Null signature types must be rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOfNull() {
        SignatureAlgorithms.of(null);
    }
}
