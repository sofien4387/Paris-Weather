package com.mtp.test.parisweather.managers.network;

import com.mtp.test.parisweather.models.weather_reports.ModelWeatherReport;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * This is interface used for centralize the way of communication with Weather API.
 *
 * @author Rahmouni Sofien
 */
public interface ParisWeatherReportDetails {

    @GET("/daily?id=2988507")
    void getWeatherReportDetails(Callback<ModelWeatherReport> cb);
}
