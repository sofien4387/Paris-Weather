package com.mtp.test.parisweather.models.weather_reports;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * Coordinate  model.
 *
 * @author Rahmouni Sofien
 */
public class Coordinate extends SugarRecord {

    private long cityID;
    @Expose
    private float lon;
    @Expose
    private float lat;

    public Coordinate() {
    }

    public Coordinate(long cityID, float lon, float lat) {
        this.cityID = cityID;
        this.lon = lon;
        this.lat = lat;
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


