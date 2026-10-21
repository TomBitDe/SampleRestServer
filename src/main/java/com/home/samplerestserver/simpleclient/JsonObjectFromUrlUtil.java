package com.home.samplerestserver.simpleclient;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Helper class.
 */
public class JsonObjectFromUrlUtil {
    /**
     * Get a JSON object from an URL.
     *
     * @param url the URL to fetch from
     *
     * @return the JSON object
     */
    public static JsonObject getJsonObjectFromUrl(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Problem with the Url " + url + ": " + conn.getResponseMessage());
            }
            try (JsonReader jsonRdr = Json.createReader((InputStream) conn.getContent())) {
                return jsonRdr.readObject();
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Problem with the Url: " + url, ex);
        }
    }
}
