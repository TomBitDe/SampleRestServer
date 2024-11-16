package com.home.samplerestserver.simpleclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonNumber;
import javax.json.JsonObject;


/**
 * JSON-REST-Client fuer den Bitcoin-Kurs, Powered by CoinDesk
 */
public class BitcoinRestClient {
    private static final Logger LOG = Logger.getLogger(BitcoinRestClient.class.getName());
    

    /**
     * Start the BitcoinRestClient.
     *
     * @param args no arguments needed so far
     */
    public static void main(String[] args) {
        final String url = "http://api.coindesk.com/v1/bpi/currentprice.json";
        JsonObject jsonObj = JsonObjectFromUrlUtil.getJsonObjectFromUrl(url);

        LOG.info("\n\n------------ Ausgabe aller Root-Key/Values:\n");
        jsonObj.entrySet().forEach(e -> LOG.log(Level.INFO, "key={0}, val={1}\n", new Object[]{e.getKey(), e.getValue()}));

        LOG.info("\n------------ Ausgabe aller Key/Values zu 'time':\n");
        JsonObject time = jsonObj.getJsonObject("time");
        time.entrySet().forEach(e -> LOG.log(Level.INFO, "key={0}, val={1}\n", new Object[]{e.getKey(), e.getValue()}));

        LOG.info("\n------------ Ausgabe aller Key/Values zu 'bpi':\n");
        JsonObject bpi = jsonObj.getJsonObject("bpi");
        bpi.entrySet().forEach(e -> LOG.log(Level.INFO, "key={0}, val={1}\n", new Object[]{e.getKey(), e.getValue()}));

        LOG.info("\n------------ Ausgabe aller Key/Values zu 'bpi.EUR':\n");
        JsonObject bpiEur = bpi.getJsonObject("EUR");
        bpiEur.entrySet().forEach(e -> LOG.log(Level.INFO, "key={0}, val={1}\n", new Object[]{e.getKey(), e.getValue()}));

        LOG.info("\n------------ Ermittlung einzelner Elemente:\n");
        String zeitpunkt = time.getString("updatedISO");
        String name = jsonObj.getString("chartName");
        String bpiEurCode = bpiEur.getString("code");
        JsonNumber bpiEurRate = bpiEur.getJsonNumber("rate_float");
        LOG.log(Level.INFO, "Ein {0} kostet {1} {2} ({3}).", new Object[]{name, bpiEurRate, bpiEurCode, zeitpunkt.replace('T', ' ')});

        LOG.info("\n-------------------------------------------------------------\n\n");
    }
}
