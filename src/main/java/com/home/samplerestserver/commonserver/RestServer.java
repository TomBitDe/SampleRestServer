package com.home.samplerestserver.commonserver;

import com.sun.net.httpserver.HttpServer;
import java.net.URI;
import javax.swing.JOptionPane;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Hello world!
 * <p>
 */
public class RestServer {
    public static void main(String[] args) {
        ResourceConfig rc = new ResourceConfig().packages("com.home.samplerestserver.commonserver");
        HttpServer server = JdkHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/rest"), rc);
        JOptionPane.showMessageDialog(null, "Ende");
        server.stop(0);
    }
}
