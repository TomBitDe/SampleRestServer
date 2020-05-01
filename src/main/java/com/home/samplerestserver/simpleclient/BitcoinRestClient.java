package com.home.samplerestserver.simpleclient;

import javax.json.JsonNumber;
import javax.json.JsonObject;

/**
 * JSON-REST-Client fuer den Bitcoin-Kurs, Powered by CoinDesk, null {@link "http://www.coindesk.com/api/"}
 * {@link "http://www.coindesk.com/price/"}
 * {@link "http://api.coindesk.com/v1/bpi/currentprice.json"}
 */
public class BitcoinRestClient {

    /**
     * Start the BitcoinRestClient.
     *
     * @param args no arguments needed so far
     */
    public static void main(String[] args) {
        final String url = "http://api.coindesk.com/v1/bpi/currentprice.json";
        JsonObject jsonObj = JsonObjectFromUrlUtil.getJsonObjectFromUrl(url);

        System.out.println("\n\n------------ Ausgabe aller Root-Key/Values:\n");
        jsonObj.entrySet().forEach(e -> System.out.println("key=" + e.getKey() + ", val=" + e.getValue() + "\n"));

        System.out.println("\n------------ Ausgabe aller Key/Values zu 'time':\n");
        JsonObject time = jsonObj.getJsonObject("time");
        time.entrySet().forEach(e -> System.out.println("key=" + e.getKey() + ", val=" + e.getValue() + "\n"));

        System.out.println("\n------------ Ausgabe aller Key/Values zu 'bpi':\n");
        JsonObject bpi = jsonObj.getJsonObject("bpi");
        bpi.entrySet().forEach(e -> System.out.println("key=" + e.getKey() + ", val=" + e.getValue() + "\n"));

        System.out.println("\n------------ Ausgabe aller Key/Values zu 'bpi.EUR':\n");
        JsonObject bpiEur = bpi.getJsonObject("EUR");
        bpiEur.entrySet().forEach(e -> System.out.println("key=" + e.getKey() + ", val=" + e.getValue() + "\n"));

        System.out.println("\n------------ Ermittlung einzelner Elemente:\n");
        String zeitpunkt = time.getString("updatedISO");
        String name = jsonObj.getString("chartName");
        String bpiEurCode = bpiEur.getString("code");
        JsonNumber bpiEurRate = bpiEur.getJsonNumber("rate_float");
        System.out.println("Ein " + name + " kostet " + bpiEurRate + " " + bpiEurCode + " (" + zeitpunkt.replace('T', ' ') + ").");

        System.out.println("\n-------------------------------------------------------------\n\n");
    }
}
