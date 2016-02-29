package com.mtp.test.parisweather.models.weather_reports;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Coordinate  model.
 *
 * @author Rahmouni Sofien
 */

public class Coordinate extends SugarRecord{

    @Expose
    private float lon;
    @Expose
    private float lat;

    public Coordinate(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Coordinate() {
    }


    /**
     * @return The lon
     */
    public float getLon() {
        return lon;
    }

    /**
     * @param lon The lon
     */
    public void setLon(float lon) {
        this.lon = lon;
    }

    /**
     * @return The lat
     */
    public float getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(float lat) {
        this.lat = lat;
    }
}


