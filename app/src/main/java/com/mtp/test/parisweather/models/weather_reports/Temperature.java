package com.mtp.test.parisweather.models.weather_reports;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * This class of temperature model.
 *
 * @author Rahmouni Sofien
 */
public class Temperature extends SugarRecord{

    @Expose
    private float day;
    @Expose
    private float min;
    @Expose
    private float max;
    @Expose
    private float night;
    @Expose
    private float eve;
    @Expose
    private float morn;

    public Temperature(float day, float max, float morn, float eve, float night, float min) {
        this.day = day;
        this.max = max;
        this.morn = morn;
        this.eve = eve;
        this.night = night;
        this.min = min;
    }

    public Temperature() {
    }

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getNight() {
        return night;
    }

    public void setNight(float night) {
        this.night = night;
    }

    public float getEve() {
        return eve;
    }

    public void setEve(float eve) {
        this.eve = eve;
    }

    public float getMorn() {
        return morn;
    }

    public void setMorn(float morn) {
        this.morn = morn;
    }

}
