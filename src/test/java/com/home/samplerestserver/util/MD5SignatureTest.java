package com.home.samplerestserver.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the legacy MD5 signature strategy.
 */
public class MD5SignatureTest {
    private static final String KEY = "key";
    private static final String DATA = "The quick brown fox jumps over the lazy dog";

    private final SignatureAlgorithm signature = new MD5Signature();

    /**
     * No need to put some code in the constructor until now.
     */
    public MD5SignatureTest() {
    }

    /**
     * Known MD5 test vector must be produced.
     */
    @Test
    public void testCalculateKnownVector() throws NoSuchAlgorithmException, InvalidKeyException {
        Assert.assertEquals("0D648EFA4A820D7BDEAF769A61703C49", signature.calculate(DATA, KEY));
    }

    /**
     * A valid signature must be verified successfully.
     */
    @Test
    public void testVerifySuccess() throws NoSuchAlgorithmException, InvalidKeyException {
        String value = signature.calculate(DATA, KEY);

        Assert.assertTrue(signature.verify(DATA, value, KEY));
    }

    /**
     * Verification must be case-insensitive regarding the hex representation.
     */
    @Test
    public void testVerifyLowerCase() throws NoSuchAlgorithmException, InvalidKeyException {
        String value = signature.calculate(DATA, KEY).toLowerCase();

        Assert.assertTrue(signature.verify(DATA, value, KEY));
    }

    /**
     * A wrong key must not verify.
     */
    @Test
    public void testVerifyWrongKey() throws NoSuchAlgorithmException, InvalidKeyException {
        String value = signature.calculate(DATA, KEY);

        Assert.assertFalse(signature.verify(DATA, value, "wrongKey"));
    }

    /**
     * Tampered data must not verify.
     */
    @Test
    public void testVerifyTamperedData() throws NoSuchAlgorithmException, InvalidKeyException {
        String value = signature.calculate(DATA, KEY);

        Assert.assertFalse(signature.verify(DATA + "!", value, KEY));
    }

    /**
     * A null or malformed signature must not verify.
     */
    @Test
    public void testVerifyNullAndMalformed() throws NoSuchAlgorithmException, InvalidKeyException {
        Assert.assertFalse(signature.verify(DATA, null, KEY));
        Assert.assertFalse(signature.verify(DATA, "not-hex", KEY));
        Assert.assertFalse(signature.verify(DATA, "ABC", KEY));
    }

    /**
     * Null or empty keys must be rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateNullKey() throws NoSuchAlgorithmException, InvalidKeyException {
        signature.calculate("{}", null);
    }

    /**
     * Empty keys must be rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateEmptyKey() throws NoSuchAlgorithmException, InvalidKeyException {
        signature.calculate("{}", "");
    }
}
