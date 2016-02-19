package com.mtp.test.parisweather.managers.db;

import com.mtp.test.parisweather.models.weather_reports.City;
import com.mtp.test.parisweather.models.weather_reports.Coordinate;
import com.mtp.test.parisweather.models.weather_reports.ListWeather;
import com.mtp.test.parisweather.models.weather_reports.ModelWeatherReport;
import com.mtp.test.parisweather.models.weather_reports.Temperature;
import com.mtp.test.parisweather.models.weather_reports.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Database manager.
 *
 * @author Rahmouni Sofien
 */
public class ParisWeatherDAO {

    /**
     * Constructor Paris Weather DAO.
     */
    public ParisWeatherDAO() {
    }

    /**
     * Save Weather Report in the local storage.
     *
     * @param modelWR model report
     */
    public void saveWeatherReportCache(ModelWeatherReport modelWR) {

        String name = modelWR.getCity().getName();
        Coordinate coord = modelWR.getCity().getCoordinate();
        String country = modelWR.getCity().getCountry();
        int population = modelWR.getCity().getPopulation();

        City cityCache = new City(name, coord, country, population);
        cityCache.save();

        List<ListWeather> lsW = modelWR.getListWeather();

        for (ListWeather l : lsW) {

            float date = l.getTemperature().getDay();
            float min = l.getTemperature().getMin();
            float max = l.getTemperature().getMax();
            float night = l.getTemperature().getNight();
            float eve = l.getTemperature().getEve();
            float morn = l.getTemperature().getMorn();

            Temperature temp = new Temperature(date, min, max, night, eve, morn);
            temp.save();

            int humidity = l.getHumidity();
            float pressure = l.getPressure();
            float rain = l.getRain();
            int clouds = l.getClouds();
            int deg = l.getDeg();
            long dateDesc = l.getDate();
            float speed = l.getSpeed();


            List<Weather> listWeather = l.getWeather();

            for (Weather w : listWeather) {
                String main = w.getMain();
                String description = w.getDescription();
                String icon = w.getIcon();
                Weather weatherCache = new Weather(main, description, icon);
                weatherCache.save();
            }

            ListWeather wDesc = new ListWeather(dateDesc, temp, pressure, humidity, listWeather, speed, deg, clouds, rain);
            wDesc.save();
        }

    }

    /**
     * Delete weather report from the data base.
     */
    public void deleteWeatherReportCache() {

        City.deleteAll(City.class);
        Temperature.deleteAll(Temperature.class);
        Weather.deleteAll(Weather.class);
        ListWeather.deleteAll(ListWeather.class);

    }

    /**
     * Getter of Model Weather Report.
     *
     * @return instance of Model Weather Report.
     */
    public ModelWeatherReport getGetModelWeatherReport() {

        ModelWeatherReport mWR = new ModelWeatherReport();

        City cityCache = City.findById(City.class, 1L);

        long count = Weather.count(Weather.class, null, null);

        List<ListWeather> lW = new ArrayList<>();

        for (long i = 1; i <= count; i++) {

            Weather weatherCache = Weather.findById(Weather.class, i);
            Temperature temp = Temperature.findById(Temperature.class, i);
            ListWeather wDesc = Weather.findById(ListWeather.class, i);

            Temperature temperature = new Temperature();
            temperature.setDay(temp.getDay());
            temperature.setEve(temp.getEve());
            temperature.setMorn(temp.getMorn());
            temperature.setMin(temp.getMin());
            temperature.setMax(temp.getMax());
            temperature.setNight(temp.getNight());

            ListWeather lWeather = new ListWeather();

            lWeather.setTemperature(temperature);
            lWeather.setClouds(wDesc.getClouds());
            lWeather.setDate(wDesc.getDate());
            lWeather.setHumidity(wDesc.getHumidity());
            lWeather.setDeg(wDesc.getDeg());
            lWeather.setPressure(wDesc.getPressure());
            lWeather.setSpeed(wDesc.getSpeed());
            lWeather.setRain(wDesc.getRain());

            List<Weather> listW = new ArrayList<>();

            Weather w = new Weather();
            w.setDescription(weatherCache.getDescription());
            w.setMain(weatherCache.getMain());
            w.setIcon(weatherCache.getIcon());

            listW.add(w);
            lWeather.setWeather(listW);
            lW.add(lWeather);

        }

        mWR.setListWeather(lW);

        City c = new City();

        Coordinate cor = new Coordinate();
        cor.setLat(cityCache.getCoordinate().getLat());
        cor.setLon(cityCache.getCoordinate().getLon());
        c.setCoordinate(cor);

        c.setCountry(cityCache.getCountry());
        c.setPopulation(cityCache.getPopulation());
        c.setName(cityCache.getName());

        mWR.setCity(c);

        return mWR;
    }

    /**
     * Check the weather report database is empty.
     *
     * @return <code>true</code> means the weather report is empty.
     */
    public boolean isWeatherReportEmpty() {

        City cityCache = City.findById(City.class, 1L);

        return cityCache == null;
    }

}
