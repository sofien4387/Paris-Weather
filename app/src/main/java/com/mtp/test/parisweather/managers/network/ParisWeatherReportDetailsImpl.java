package com.mtp.test.parisweather.managers.network;

/**
 * This is class used for centralize the way of communication with Weather API:
 *
 * - getter of details weather report.
 *
 * @author Rahmouni Sofien
 */
public class ParisWeatherReportDetailsImpl extends ParisWeatherRestClient {
    public ParisWeatherReportDetailsImpl() {
        super(ParisWeatherReportDetails.class);
    }

    public ParisWeatherReportDetails getWeatherReportDetailsInstance() {
        ParisWeatherReportDetails wR = (ParisWeatherReportDetails) restInterface;
        return wR;
    }
}
