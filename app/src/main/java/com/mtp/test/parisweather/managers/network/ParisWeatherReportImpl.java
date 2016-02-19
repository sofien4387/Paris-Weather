package com.mtp.test.parisweather.managers.network;

/**
 * Created by Sofien on 02/02/2016.
 */
public class ParisWeatherReportImpl extends ParisWeatherRestClient {

    public ParisWeatherReportImpl() {
        super(ParisWeatherReport.class);
    }

    public ParisWeatherReport getWeatherReportInstance() {
        ParisWeatherReport wR = (ParisWeatherReport) restInterface;
        return wR;
    }
}
