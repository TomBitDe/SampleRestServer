package com.home.samplerestserver.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Format a LogRecord into a brief format. The formatter creates a shorter output format for java logging. The output
 * looks like this (single line):
 * <p>
 * 2009.12.30 10:10:06:732 root[INFO|main]: This is the log message
 */
public class JavaLoggingBriefFormatter extends Formatter {
    /**
     * The default date format to use in the log message.
     */
    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd h:mm:ss.SSS";
    /**
     * The line separator to use when formatting.
     */
    private static final String LINE_SEP = System.getProperty("line.separator");
    /**
     * The date format to use in the log message.
     */
    private final DateFormat format = new SimpleDateFormat(DATE_FORMAT_DEFAULT);

    /**
     * A custom format implementation that is designed for brevity.
     *
     * @param rec the log record
     *
     * @return formatted string
     */
    @Override
    public String format(LogRecord rec) {
        String loggerName = rec.getLoggerName();
        if (loggerName == null) {
            loggerName = "root";
        }
        return (String.valueOf(String.valueOf(String.valueOf(format.format(new Date(rec.getMillis()))
                + " [") + String.valueOf(rec.getLevel())) + "] [" + Thread.currentThread().getName() + "] "
                + loggerName
                + " - "
                + formatMessage(rec)) + Character.toString(' ')
                + LINE_SEP);
    }
}
