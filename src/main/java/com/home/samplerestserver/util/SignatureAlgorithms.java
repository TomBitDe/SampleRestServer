package com.home.samplerestserver.util;

import java.util.Locale;

/**
 * Factory to select a signature strategy by its signature type.
 */
public final class SignatureAlgorithms {
    public static final String MD5 = "MD5";
    public static final String HMAC_SHA256 = "HmacSHA256";

    private static final SignatureAlgorithm MD5_SIGNATURE = new MD5Signature();
    private static final SignatureAlgorithm HMAC_SHA256_SIGNATURE = new HmacSha256Signature();

    private SignatureAlgorithms() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Return the signature strategy for the given signature type (case-insensitive).
     *
     * @param signType the signature type, e.g. "MD5" or "HmacSHA256"
     * @return the signature strategy
     */
    public static SignatureAlgorithm of(String signType) {
        if (signType == null || signType.trim().isEmpty()) {
            throw new IllegalArgumentException("signType must not be null or empty");
        }

        switch (signType.trim().toUpperCase(Locale.ROOT)) {
            case "MD5":
                return MD5_SIGNATURE;
            case "HMACSHA256":
            case "HMAC-SHA256":
            case "HMAC_SHA256":
                return HMAC_SHA256_SIGNATURE;
            default:
                throw new IllegalArgumentException("Unsupported signature type: " + signType);
        }
    }
}
