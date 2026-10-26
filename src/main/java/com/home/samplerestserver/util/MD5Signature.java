package com.home.samplerestserver.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Legacy MD5 signature strategy (kept for backward compatibility).
 */
public final class MD5Signature implements SignatureAlgorithm {
    private static final String ALGORITHM = "MD5";

    @Override
    public String calculate(String jsonData, String key) throws NoSuchAlgorithmException {
        if (jsonData == null) {
            throw new IllegalArgumentException("jsonData must not be null");
        }
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key must not be null or empty");
        }

        String stringToSign = jsonData + "&" + key;

        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        byte[] hashBytes = md.digest(stringToSign.getBytes(StandardCharsets.UTF_8));

        return Signatures.toHex(hashBytes);
    }

    @Override
    public boolean verify(String jsonData, String receivedSignature, String key)
            throws NoSuchAlgorithmException {
        return Signatures.constantTimeEquals(calculate(jsonData, key), receivedSignature);
    }
}
