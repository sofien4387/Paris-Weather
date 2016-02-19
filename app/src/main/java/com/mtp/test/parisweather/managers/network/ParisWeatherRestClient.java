package com.mtp.test.parisweather.managers.network;

import static com.mtp.test.parisweather.utils.ParisWeatherConstants.API_KEY;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.API_URL;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * This is interface used for centralize the way of communication with Weather API:
 * - centralize the using of key api.
 * - centralize the building Rest adapter.
 *
 * @author Rahmouni Sofien
 */
public abstract class ParisWeatherRestClient implements RequestInterceptor {

    protected Object restInterface;
    private static final String APP_ID = "APPID";

    public ParisWeatherRestClient(Class<?> cls) {

        // Init object of RestAdapter
        RestAdapter adapter = new RestAdapter.Builder().setRequestInterceptor(this).setEndpoint(API_URL).build();

        // Create Rest Adapter Service
        restInterface = adapter.create(cls);
    }

    @Override
    public void intercept(RequestFacade request) {
        // Add api_key as query param to every api request
        request.addQueryParam(APP_ID, API_KEY);
    }
}
