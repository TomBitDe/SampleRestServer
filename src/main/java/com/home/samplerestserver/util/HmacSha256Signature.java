package com.home.samplerestserver.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC-SHA256 signature strategy.
 */
public final class HmacSha256Signature implements SignatureAlgorithm {
    private static final String ALGORITHM = "HmacSHA256";

    @Override
    public String calculate(String jsonData, String key)
            throws NoSuchAlgorithmException, InvalidKeyException {
        if (jsonData == null) {
            throw new IllegalArgumentException("jsonData must not be null");
        }
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key must not be null or empty");
        }

        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM));
        byte[] hashBytes = mac.doFinal(jsonData.getBytes(StandardCharsets.UTF_8));

        return Signatures.toHex(hashBytes);
    }

    @Override
    public boolean verify(String jsonData, String receivedSignature, String key)
            throws NoSuchAlgorithmException, InvalidKeyException {
        return Signatures.constantTimeEquals(calculate(jsonData, key), receivedSignature);
    }
}
