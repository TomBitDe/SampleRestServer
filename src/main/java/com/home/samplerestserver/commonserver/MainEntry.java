package com.home.samplerestserver.commonserver;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * The Main entry point for calls from or into the "native" C world.
 * <p>
 * Declare calls to JNI here.<br>
 * Provide methods startWS() and stopWS() that can be called from the C environment.<br>
 * The main method is the starter if not running in the C environment.<br>
 */
public class MainEntry {
    private static final Logger LOG = Logger.getLogger(MainEntry.class.getName());

    /**
     * Used for global synchronization, if needed.
     */
    public static final Boolean GLOBAL_LOCK = Boolean.FALSE;

    /**
     * Indicates if the jar is running without the TSW process
     */
    public static Boolean standAloneMode = Boolean.FALSE;

    /*
     * Calls to native C-functions.
     * The calls will be redirected to the kwsrcv-module.
     * See kwsrcv.h for more details. Examples are the following calls:
     */
    /**
     * A sample native C call.
     *
     * @param inBuffer just a text
     *
     * @return the inBuffer text echoed
     */
    public static native String kwsrcvrcv(String inBuffer);

    /**
     * A sample native C call to create a PLOG message.
     *
     * @param plms
     * @param domn
     * @param phbl
     * @param mandant
     * @param refNo
     * @param idx
     * @param paramsdomn
     */
    public static native void kwsrcvplog(String plms, String domn, String phbl, String mandant, long refNo, int idx, String paramsdomn);

    /**
     * Called by native module to start and publish the RestServices.
     * <p>
     * Start the logfile watchdog here.<br>
     *
     * @return false in any error case else true
     */
    public static boolean startWS() {
        LOG.info("Enter startWS");
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerImpl());

        String configFile = System.getProperty("log4j.configurationFile", "log4j.xml");
        DOMConfigurator.configureAndWatch(configFile, 30 * 1000);

        try {
            return RestServer.startServices();
        }
        catch (Throwable e) {
            LOG.error(e, e);
        }
        finally {
            LOG.info("Leave startRS");
        }
        return false;
    }

    /**
     * Called by native module to stop the published RestServices.
     *
     * @return false in any error case else true
     */
    public static boolean stopWS() {
        LOG.info("Enter stopWS");
        try {
            return RestServer.stopServices();
        }
        catch (Exception e) {
            LOG.error(e, e);
        }
        finally {
            LOG.info("Leave stopWS");
        }
        return false;
    }

    /**
     * Starts and publishes the WebServices during startup.
     * <p>
     * Set the standAloneMode true because this is not running in a TSW enviroment.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        standAloneMode = true;

        startWS();
    }
}
