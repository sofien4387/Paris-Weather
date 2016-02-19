package com.mtp.test.parisweather.models.weather_reports;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * City model.
 *
 * @author Rahmouni Sofien
 */
@Table
public class City  extends SugarRecord{

    @Expose
    private String name;
    @Expose
    private Coordinate coord;
    @Expose
    private String country;
    @Expose
    private int population;

    public City() {
    }

    public City(String name, Coordinate coord, String country, int population) {

        this.name = name;
        this.country = country;
        this.population = population;
        this.coord = coord;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Coordinate getCoordinate() {
        return coord;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coord = coordinate;
    }

}
