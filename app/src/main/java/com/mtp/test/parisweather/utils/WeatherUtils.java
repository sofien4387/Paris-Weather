package com.mtp.test.parisweather.utils;

/**
 * This class of paris weather utils used to centralize any method
 * which have relation with weather using.
 * E.g:
 * - convert speed of winds to given unity : km/hours
 *
 * @author Rahmouni Sofien
 */
public class WeatherUtils {

    /**
     * Convert speed wind from metre/sec to Km/hours.
     *
     * @param speedWind speed wind  metre/sec.
     * @return speed wind km/hours
     */
    public static int convertSpeedWind(float speedWind) {

        return (int) (speedWind * 3.6);
    }

    /**
     * Getter degree of winds.
     * @param degree degree of winds
     * @return integer between 1 and 4 ;
     * 1 means is under 180 degree and ... etc
     */
    public  static int getDegreeWinds(int degree) {
        if (degree > 0 && degree < 180) {
            return 1;
        } else if (degree >= 180 && degree < 270) {
            return 2;
        } else if (degree >= 270 && degree < 360) {
            return 3;
        } else {
            return 4;
        }
    }
}
