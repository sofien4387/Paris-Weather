package com.mtp.test.parisweather.managers.network;

import com.mtp.test.parisweather.models.weather_reports.ModelWeatherReport;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * This is interface used for centralize the way of communication with Weather API for service of
 * get of the report details by different language : French and  the default language of response.
 *
 * @author Rahmouni Sofien
 */
public interface ParisWeatherReport {

    @GET("/daily?q=Paris&units=metric&cnt=5")
    void getWeatherReportDefault(Callback<ModelWeatherReport> cb);

    @GET("/daily?q=Paris&units=metric&cnt=5&lang=fr")
    void getWeatherReportFrench(Callback<ModelWeatherReport> cb);
}
