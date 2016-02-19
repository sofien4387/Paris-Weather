package com.mtp.test.parisweather.activities;

import static com.mtp.test.parisweather.utils.ParisWeatherConstants.ICON_EXTENSION;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.ITEM_PARIS_WEATHER_KEY;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.UNIT_RAIN;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.UNIT_TEMP_CELSIUS;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.UNIT_WIND_SPEED;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.PRESSURE_UNITY;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtp.test.parisweather.R;
import com.mtp.test.parisweather.application.ParisWeatherApplication;
import com.mtp.test.parisweather.models.weather_reports.City;
import com.mtp.test.parisweather.models.weather_reports.ListWeather;
import com.mtp.test.parisweather.models.weather_reports.ModelWeatherReport;
import com.mtp.test.parisweather.models.weather_reports.Temperature;
import com.mtp.test.parisweather.utils.DateUtils;
import com.mtp.test.parisweather.utils.FileUtils;
import com.mtp.test.parisweather.utils.ParisWeatherConstants;
import com.mtp.test.parisweather.utils.TextUtils;
import com.mtp.test.parisweather.utils.WeatherUtils;

import java.io.File;


public class ParisWeatherDetailsActivity extends ParisWeatherBaseActivity {

    private ParisWeatherApplication mApplication;

    private String mCountry;
    private String mNameCountry;
    private float mMin;
    private float mMax;
    private float mDay;
    private float mMorning;
    private float mEvening;
    private float mNight;
    private int mClouds;
    private int mDegree;
    private long mDate;
    private float mRain;
    private float mPressure;
    private int mHumidity;
    private float mSpeed;
    private String mDescription;
    private String mMainStateTemp;
    private String mIcon;

    private TextView mDateView;
    private ImageView mIconView;
    private TextView mTempView;
    private TextView mMainTempStateView;
    private TextView mDescriptionView;
    private TextView mAverageView;
    private TextView mMinTempView;
    private TextView mMaxTempView;
    private TextView mEveningView;
    private TextView mSpeedView;
    private TextView mCloudsView;
    private TextView mDirectionWindView;
    private TextView mPressureView;
    private TextView mHumidityView;
    private TextView mMorningView;
    private TextView mNightView;
    private TextView mCountryView;
    private TextView mRainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paris_weather_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get the paris weather application instance
        mApplication = (ParisWeatherApplication) getApplication();

        initData();

        initViews();

        initEvents();

        fillViewData();
    }

    private void initData() {

        int itemId = getIntent().getIntExtra(ITEM_PARIS_WEATHER_KEY, 0);

        ModelWeatherReport modelWR = mApplication
                .getManagerDAO()
                .getGetModelWeatherReport();

        City city = modelWR.getCity();

        ListWeather weatherDescription = modelWR
                .getListWeather()
                .get(itemId);

        mCountry = city.getCountry();
        mNameCountry = city.getName();

        Temperature temperature = weatherDescription.getTemperature();
        mMin = temperature.getMin();
        mMax = temperature.getMax();
        mDay = temperature.getDay();
        mMorning = temperature.getMorn();
        mEvening = temperature.getEve();
        mNight = temperature.getNight();

        mDate = weatherDescription.getDate();
        mClouds = weatherDescription.getClouds();
        mDegree = weatherDescription.getDeg();
        mRain = weatherDescription.getRain();
        mPressure = weatherDescription.getPressure();
        mHumidity = weatherDescription.getHumidity();
        mSpeed = weatherDescription.getSpeed();
        mDescription = weatherDescription.getWeather().get(0).getDescription();
        mMainStateTemp = weatherDescription.getWeather().get(0).getMain();
        mIcon = weatherDescription.getWeather().get(0).getIcon();

    }

    private void initViews() {

        mDateView = (TextView) findViewById(R.id.date);
        mNightView = (TextView) findViewById(R.id.night);
        mIconView = (ImageView) findViewById(R.id.weatherIcon);
        mTempView = (TextView) findViewById(R.id.temperature);
        mMainTempStateView = (TextView) findViewById(R.id.main);
        mCountryView = (TextView) findViewById(R.id.country);
        mDescriptionView = (TextView) findViewById(R.id.description);
        mAverageView = (TextView) findViewById(R.id.average);
        mMinTempView = (TextView) findViewById(R.id.minmum);
        mMaxTempView = (TextView) findViewById(R.id.maximum);
        mEveningView = (TextView) findViewById(R.id.evening);
        mMorningView = (TextView) findViewById(R.id.morning);
        mRainView = (TextView) findViewById(R.id.rain);
        mSpeedView = (TextView) findViewById(R.id.speed);
        mCloudsView = (TextView) findViewById(R.id.clouds);
        mDirectionWindView = (TextView) findViewById(R.id.direction);
        mPressureView = (TextView) findViewById(R.id.pressure);
        mHumidityView = (TextView) findViewById(R.id.humidity);
    }

    private void fillViewData() {

        String formattedDate = DateUtils.convertDate(mDate);

        mDateView.setText(TextUtils.capitalizeFirstChar(formattedDate));

        mCountryView.setText(mCountry + "," + TextUtils.capitalizeFirstChar(mNameCountry));

        mIconView.setImageURI(accessToIconURI());

        mMainTempStateView.setText(mMainStateTemp);

        mNightView.setText(String.valueOf((int) mNight) + UNIT_TEMP_CELSIUS);
        mTempView.setText(String.valueOf((int) mDay) + UNIT_TEMP_CELSIUS);
        mDescriptionView.setText(TextUtils.capitalizeFirstChar(mDescription));
        mAverageView.setText(String.valueOf((int) mDay) + UNIT_TEMP_CELSIUS);
        mMinTempView.setText(String.valueOf((int) mMin) + UNIT_TEMP_CELSIUS);
        mMaxTempView.setText(String.valueOf((int) mMax) + UNIT_TEMP_CELSIUS);
        mEveningView.setText(String.valueOf((int) mEvening) + UNIT_TEMP_CELSIUS);
        mMorningView.setText(String.valueOf((int) mMorning) + UNIT_TEMP_CELSIUS);
        mRainView.setText(String.valueOf(mRain) + UNIT_RAIN);

        // Get speed wind weather
        int spKmHours = WeatherUtils.convertSpeedWind(mSpeed);

        mSpeedView.setText(String.valueOf(spKmHours) + UNIT_WIND_SPEED);
        mCloudsView.setText(mClouds + ParisWeatherConstants.PERCENT);
        mDirectionWindView.setText(getDirectionWinds());
        mPressureView.setText(String.valueOf(mPressure) +  PRESSURE_UNITY);
        mHumidityView.setText(String.valueOf(mHumidity) + ParisWeatherConstants.PERCENT);
    }

    private void initEvents() {

    }

    private Uri accessToIconURI() {
        String uri = FileUtils.getCacheDirectoryApplication() + File.separator + mIcon + ICON_EXTENSION;
        return Uri.parse(uri);
    }

    private String getDirectionWinds() {

        String west = getString(R.string.west);
        String south = getString(R.string.south);
        String east = getString(R.string.east);
        String north = getString(R.string.north);

        int deg = WeatherUtils.getDegreeWinds(mDegree);

        String degDirect = null;

        switch (deg) {
            case 1:
                degDirect = east;
                break;
            case 2:
                degDirect = south;
                break;
            case 3:
                degDirect = west;
                break;
            case 4:
                degDirect = north;
                break;

        }
        return degDirect;
    }

}
