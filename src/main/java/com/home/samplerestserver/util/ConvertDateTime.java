package com.home.samplerestserver.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

public class ConvertDateTime {
    private static final Logger log = Logger.getLogger(ConvertDateTime.class.getName());

    private static DatatypeFactory dtf;

    public static XMLGregorianCalendar toXmlDate(final String dttm) {
        if (dttm != null && !dttm.trim().isEmpty()) {
            try {
                int year, month, day, hour = 0, minute = 0, second = 0;
                year = Integer.parseInt(dttm.substring(0, 4));
                month = Integer.parseInt(dttm.substring(4, 6));
                day = Integer.parseInt(dttm.substring(6, 8));
                return dtf.newXMLGregorianCalendar(year, month, day, hour, minute, second, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
            } catch (NumberFormatException ex) {
                log.severe(ex.getMessage());
            }
        }
        return null;
    }

    public static XMLGregorianCalendar toXmlTime(final String dttm) {
        if (dttm != null && !dttm.trim().isEmpty()) {
            try {
                int year = 0, month = 0, day = 0, hour, minute, second;
                hour = Integer.parseInt(dttm.substring(8, 10));
                minute = Integer.parseInt(dttm.substring(10, 12));
                second = Integer.parseInt(dttm.substring(12, 14));
                return dtf.newXMLGregorianCalendar(year, month, day, hour, minute, second, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
            } catch (NumberFormatException ex) {
                log.severe(ex.getMessage());
            }
        }
        return null;
    }

    public static XMLGregorianCalendar toXmlDateTime(final String dttm) {
        if (dttm != null && !dttm.trim().isEmpty()) {
            try {
                int year, month, day, hour, minute, second;
                year = Integer.parseInt(dttm.substring(0, 4));
                month = Integer.parseInt(dttm.substring(4, 6));
                day = Integer.parseInt(dttm.substring(6, 8));
                hour = Integer.parseInt(dttm.substring(8, 10));
                minute = Integer.parseInt(dttm.substring(10, 12));
                second = Integer.parseInt(dttm.substring(12, 14));
                return dtf.newXMLGregorianCalendar(year, month, day, hour, minute, second, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
            } catch (NumberFormatException ex) {
                log.severe(ex.getMessage());
            }
        }
        return null;
    }

    public static Duration toXMLDuration(final String tm) {
        if (tm != null) {
            try {
                int hour = 0, minute = 0, second = 0;
		if(!tm.trim().isEmpty()) {
                    hour = Integer.parseInt(tm.substring(0, 2));
                    minute = Integer.parseInt(tm.substring(2, 4));
                    second = Integer.parseInt(tm.substring(4, 6));
                }
                return dtf.newDuration(true, 0, 0, 0, hour, minute, second);
            } catch (NumberFormatException ex) {
                log.severe(ex.getMessage());
            }
        }
        return null;
    }

    public static String toTM(Duration duration) {
        if (duration == null) {
            return null;
        }
        int hour = duration.getHours();
        int minute = duration.getMinutes();
        int second = duration.getSeconds();
        return StringUtil.toLengthString(hour, 2)
                + StringUtil.toLengthString(minute, 2)
                + StringUtil.toLengthString(second, 2);
    }

    public static String toDTTM(XMLGregorianCalendar xmlDateTime) {
        if (xmlDateTime == null) {
            return null;
        }
        int year = xmlDateTime.getYear();
        int month = xmlDateTime.getMonth();
        int day = xmlDateTime.getDay();
        int hour = xmlDateTime.getHour();
        int minute = xmlDateTime.getMinute();
        int second = xmlDateTime.getSecond();
        return StringUtil.toLengthString(year, 4)
                + StringUtil.toLengthString(month, 2)
                + StringUtil.toLengthString(day, 2)
                + StringUtil.toLengthString(hour, 2)
                + StringUtil.toLengthString(minute, 2)
                + StringUtil.toLengthString(second, 2);
    }

    public static String toDT(XMLGregorianCalendar xmlDateTime) {
        if (xmlDateTime == null) {
            return null;
        }
        int year = xmlDateTime.getYear();
        int month = xmlDateTime.getMonth();
        int day = xmlDateTime.getDay();
        return StringUtil.toLengthString(year, 4)
                + StringUtil.toLengthString(month, 2)
                + StringUtil.toLengthString(day, 2);
    }

    public static String toTM(XMLGregorianCalendar xmlDateTime) {
        if (xmlDateTime == null) {
            return null;
        }
        int hour = xmlDateTime.getHour();
        int minute = xmlDateTime.getMinute();
        int second = xmlDateTime.getSecond();
        return StringUtil.toLengthString(hour, 2)
                + StringUtil.toLengthString(minute, 2)
                + StringUtil.toLengthString(second, 2);
    }

    public static XMLGregorianCalendar getCurrentDateTime() {
        GregorianCalendar cal = new GregorianCalendar();
        return dtf.newXMLGregorianCalendar(cal);
    }

    public static Duration getDuration(long milliSecs) {
        return dtf.newDuration(milliSecs);
    }

    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd HHmmss.SS");
        return sdf.format(date);
    }

    static {
        try {
            dtf = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException ex) {
            log.severe(ex.toString());
        }
    }
}
