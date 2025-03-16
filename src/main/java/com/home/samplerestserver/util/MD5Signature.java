package com.home.samplerestserver.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Signature {

    private MD5Signature() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String calculateMD5Signature(String jsonData, String key) throws NoSuchAlgorithmException {
        // Combine JSON-Data and Key
        String stringToSign = jsonData + "&" + key;

        // Calculate MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(stringToSign.getBytes(StandardCharsets.UTF_8));

        // Hash in hex-format and upper letters
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString().toUpperCase();
    }

    public static boolean verifySignature(String jsonData, String receivedSignature, String key) throws NoSuchAlgorithmException {
        // Recalculate the given signature
        String calculatedSignature = calculateMD5Signature(jsonData, key);

        // Compare the calculated with the received signature
        return calculatedSignature.equals(receivedSignature);
    }
}
