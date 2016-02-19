package com.mtp.test.parisweather.models.weather_reports;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * This class of weather model.
 *
 * @author Rahmouni Sofien
 */
@Table
public class Weather extends SugarRecord{


    @Expose
    private String main;
    @Expose
    private String description;
    @Expose
    private String icon;

    public Weather(String main, String description, String icon) {
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public Weather() {
    }

    /**
     *
     * @return
     * The main
     */
    public String getMain() {
        return main;
    }

    /**
     *
     * @param main
     * The main
     */
    public void setMain(String main) {
        this.main = main;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
