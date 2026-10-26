package com.home.samplerestserver.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the HMAC-SHA256 signature strategy.
 */
public class HmacSha256SignatureTest {
    private static final String KEY = "key";
    private static final String DATA = "The quick brown fox jumps over the lazy dog";

    private final SignatureAlgorithm signature = new HmacSha256Signature();

    /**
     * No need to put some code in the constructor until now.
     */
    public HmacSha256SignatureTest() {
    }

    /**
     * Known HMAC-SHA256 test vector must be produced.
     */
    @Test
    public void testCalculateKnownVector()
            throws NoSuchAlgorithmException, InvalidKeyException {
        Assert.assertEquals(
                "F7BC83F430538424B13298E6AA6FB143EF4D59A14946175997479DBC2D1A3CD8",
                signature.calculate(DATA, KEY));
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
     * A wrong key or tampered data must not verify.
     */
    @Test
    public void testVerifyFailure() throws NoSuchAlgorithmException, InvalidKeyException {
        String value = signature.calculate(DATA, KEY);

        Assert.assertFalse(signature.verify(DATA, value, "wrongKey"));
        Assert.assertFalse(signature.verify(DATA + "!", value, KEY));
        Assert.assertFalse(signature.verify(DATA, null, KEY));
    }

    /**
     * Null or empty keys must be rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateEmptyKey() throws NoSuchAlgorithmException, InvalidKeyException {
        signature.calculate("{}", "");
    }
}
