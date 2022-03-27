package com.home.samplerestserver.commonserver;

/**
 * The Rest server MBean interface.
 */
public interface RestServerMBean {
    public boolean getRunning();

    public String getHost();
    public void setHost(String host);
    
    public String getPort();
    public void setPort(String port);
    
    public boolean startServices();
    public boolean stopServices();
}
