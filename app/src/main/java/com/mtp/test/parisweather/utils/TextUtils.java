package com.mtp.test.parisweather.utils;

/**
 * This class of text utils used to centralize any method
 * which have relation with text using.
 * E.g:
 * - capitalize first case on the given text
 *
 * @author Rahmouni Sofien
 */
public class TextUtils {

    /**
     * Capitalize the first letter of defined text.
     * @param text text which contains the first letter will be capitalize
     * @return text capitalized.
     */
    public static String capitalizeFirstChar(String text) {

        String cap = text.substring(0, 1).toUpperCase() + text.substring(1);
        return cap;
    }
}
