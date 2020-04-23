package com.home.samplerestserver.commonserver;

import javax.xml.bind.annotation.XmlRootElement;

/**
 */
@XmlRootElement
public class ServerInfo {
    private String server;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
