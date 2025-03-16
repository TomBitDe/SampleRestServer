package com.home.samplerestserver.util;

public class StringUtil {
    public static String toLengthString(long number, int length) {
        String val = "" + number;
        if(val.length() < length) {
            int remaining = length - val.length();
            StringBuilder sb = new StringBuilder(remaining);
            for(int i = 0; i < remaining; ++i) {
                sb.append("0");
            }
            return sb + val;
        }
        return val;
    }

    public static String padToLength(String orig, int length) {
        if(orig.length() < length) {
            int remaining = length - orig.length();
            StringBuilder sb = new StringBuilder(remaining);
            for(int i = 0; i < remaining; ++i) {
                sb.append(" ");
            }
            return orig + sb;
        }
        else {
            return orig.substring(0, length);
        }
    }
    
    public static String cutString(String orig, int maxLength) {
        if(orig != null) {
            if(orig.length() > maxLength) {
                orig = orig.substring(0, maxLength);
            }
            int removeTrailingBlanks = orig.length() - 1;
            while(removeTrailingBlanks >= 0 && orig.charAt(removeTrailingBlanks) == ' ') {
                --removeTrailingBlanks;
            }
            if(removeTrailingBlanks < (orig.length() - 1)) {
                return orig.substring(0, removeTrailingBlanks + 1);
            }
        }
        return orig;
    }

    public static String fixedLengthString(String orig, int fixedLength) {
        if(orig != null) {
            if(orig.length() > fixedLength) {
                return orig.substring(0, fixedLength);
            }
            else if(orig.length() < fixedLength) {
                return StringUtil.padToLength(orig, fixedLength);
            }
        }
        return orig;
    }
}
