package com.home.samplerestserver.simpleclient;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;

/**
 * Helper class.
 */
public class JsonObjectFromUrlUtil {
    private static final int CONNECT_TIMEOUT_MILLIS = 5000;
    private static final int READ_TIMEOUT_MILLIS = 5000;

    /**
     * Get a JSON object from an URL.
     *
     * @param url the URL to fetch from
     *
     * @return the JSON object
     *
     * @throws IllegalArgumentException if the URL is null, blank or malformed
     * @throws JsonObjectFromUrlException if the request fails or returns a non-200 status
     */
    public static JsonObject getJsonObjectFromUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("url must not be null or empty");
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) URI.create(url).toURL().openConnection();
            conn.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
            conn.setReadTimeout(READ_TIMEOUT_MILLIS);
            conn.setRequestProperty("Accept", "application/json");

            int statusCode = conn.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                throw new JsonObjectFromUrlException(
                        "Problem with the Url " + url + ": " + conn.getResponseMessage(),
                        statusCode);
            }

            try (InputStream in = conn.getInputStream();
                    JsonReader jsonRdr = Json.createReader(in)) {
                return jsonRdr.readObject();
            }
        }
        catch (IOException ex) {
            throw new JsonObjectFromUrlException("Problem with the Url: " + url, ex);
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
