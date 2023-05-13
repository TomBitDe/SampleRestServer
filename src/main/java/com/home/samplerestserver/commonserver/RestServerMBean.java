package com.home.samplerestserver.commonserver;

/**
 * The REST server MBean interface.
 */
public interface RestServerMBean {
    /**
     * Gets the running state of the REST server.
     * 
     * @return the running state 
     */
    public boolean getRunning();

    /**
     * Gets the host.
     * 
     * @return the host 
     */
    public String getHost();
    /**
     * Sets the host.
     * 
     * @param host the host 
     */
    public void setHost(String host);
    
    /**
     * Gets the port.
     * 
     * @return the port 
     */
    public String getPort();
    /**
     * Sets the port.
     * 
     * @param port the port 
     */
    public void setPort(String port);
    
    /**
     * Create the http server.
     *
     * @return true in any case so far
     */
    public boolean startServices();
    /**
     * Stop the http server.
     *
     * @return true in any case so far
     */
    public boolean stopServices();
}
