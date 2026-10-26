package com.home.samplerestserver.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Strategy for calculating and verifying message signatures.
 */
public interface SignatureAlgorithm {
    String calculate(String jsonData, String key)
            throws NoSuchAlgorithmException, InvalidKeyException;

    boolean verify(String jsonData, String receivedSignature, String key)
            throws NoSuchAlgorithmException, InvalidKeyException;
}
