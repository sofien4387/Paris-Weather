package com.mtp.test.parisweather.models.weather_reports;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * List Weather model.
 *
 * @author Rahmouni Sofien
 */
public class ListWeather extends SugarRecord{

    @Expose
    private long dt;
    @Expose
    private Temperature temp;
    @Expose
    private float pressure;
    @Expose
    private int humidity;
    @Expose
    private List<Weather> weather = new ArrayList<>();
    @Expose
    private float speed;
    @Expose
    private int deg;
    @Expose
    private int clouds;
    @Expose
    private float rain;

    public ListWeather() {
    }

    public ListWeather(long dt, Temperature temp, float pressure, int humidity, List<Weather> weather, float speed, int deg, int clouds, float rain) {
        this.dt = dt;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
        this.speed = speed;
        this.deg = deg;
        this.clouds = clouds;
        this.rain = rain;
    }

    public long getDate() {
        return dt;
    }

    public void setDate(long dt) {
        this.dt = dt;
    }

    public Temperature getTemperature() {
        return temp;
    }

    public void setTemperature(Temperature temperature) {
        this.temp = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }
}
