package com.mtp.test.parisweather.application;

import com.mtp.test.parisweather.managers.db.ParisWeatherDAO;
import com.mtp.test.parisweather.utils.FileUtils;
import com.orm.SugarApp;

/**
 * Paris weather application used to centralize the way to introduce any singleton initialization.
 *
 * @author Rahmouni Sofien
 */
public class ParisWeatherApplication extends SugarApp {

    private ParisWeatherDAO parisWeatherDao;

    @Override
    public void onCreate() {
        super.onCreate();

        // Delete any old cache directory application
        FileUtils.resetCacheDirectoryApplication();

        // Init DAO manager
        initManagerDAO();
    }

    /**
     * Getter the singleton instance of DAO manager.
     *
     * @return singleton instance of DAO manager.
     */
    public ParisWeatherDAO getManagerDAO() {
        return parisWeatherDao;
    }

    /**
     * Init manager DAO.
     */
    private void initManagerDAO() {
        parisWeatherDao = new ParisWeatherDAO();
    }

}
