package com.home.samplerestserver.util;

import java.security.MessageDigest;

/**
 * Shared helpers for signature strategies.
 */
final class Signatures {
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private Signatures() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    static String toHex(byte[] hashBytes) {
        StringBuilder hexString = new StringBuilder(hashBytes.length * 2);
        for (byte b : hashBytes) {
            hexString.append(HEX_DIGITS[(b >> 4) & 0x0F]);
            hexString.append(HEX_DIGITS[b & 0x0F]);
        }
        return hexString.toString();
    }

    static boolean constantTimeEquals(String calculatedSignature, String receivedSignature) {
        if (receivedSignature == null) {
            return false;
        }

        byte[] expected = hexToBytes(calculatedSignature);
        byte[] actual = hexToBytes(receivedSignature);

        return actual != null && MessageDigest.isEqual(expected, actual);
    }

    private static byte[] hexToBytes(String hex) {
        if (hex == null || hex.isEmpty() || (hex.length() & 1) != 0) {
            return null;
        }

        byte[] result = new byte[hex.length() / 2];
        for (int i = 0; i < result.length; i++) {
            int high = Character.digit(hex.charAt(i * 2), 16);
            int low = Character.digit(hex.charAt(i * 2 + 1), 16);
            if (high < 0 || low < 0) {
                return null;
            }
            result[i] = (byte) ((high << 4) | low);
        }

        return result;
    }
}
