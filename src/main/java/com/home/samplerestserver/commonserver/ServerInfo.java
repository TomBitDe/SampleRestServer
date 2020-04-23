package com.home.samplerestserver.commonserver;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The server info as XML object.
 */
@XmlRootElement
public class ServerInfo {
    private String server;

    /**
     * Access the server info.
     *
     * @return the server info
     */
    public String getServer() {
        return server;
    }

    /**
     * Set the server info.
     *
     * @param server the info value
     */
    public void setServer(String server) {
        this.server = server;
    }
}
