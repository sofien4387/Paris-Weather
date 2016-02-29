package com.mtp.test.parisweather.fragments;

import static com.mtp.test.parisweather.utils.ParisWeatherConstants.CONNECTIVITY_CHANGE_ACTION;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mtp.test.parisweather.R;
import com.mtp.test.parisweather.listener.ParisWeatherConnectionListener;
import com.mtp.test.parisweather.activities.ParisWeatherActivity;
import com.mtp.test.parisweather.adapters.ParisWeatherAdapter;
import com.mtp.test.parisweather.application.ParisWeatherApplication;
import com.mtp.test.parisweather.managers.network.ParisWeatherReportImpl;
import com.mtp.test.parisweather.models.weather_reports.ListWeather;
import com.mtp.test.parisweather.models.weather_reports.ModelWeatherReport;
import com.mtp.test.parisweather.utils.ConnectionUtils;
import com.mtp.test.parisweather.views.DialogWrapper;
import com.mtp.test.parisweather.views.ProgressDialogWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class ParisWeatherFragment extends Fragment implements Callback<ModelWeatherReport> , ParisWeatherConnectionListener {

    private static final String TAG = "ParisWeatherFragment";

    private ParisWeatherApplication mParisWeatherApp;
    private RecyclerView mRecyclerViewList;
    private ProgressDialogWrapper mProgressDialog;
    private DialogWrapper mDialog;
    private TextView mEmptyMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_paris_weather, container, false);
        mRecyclerViewList = (RecyclerView) v.findViewById(R.id.cardList);
        mEmptyMessage = (TextView)v.findViewById(R.id.emptyElement);
        mRecyclerViewList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewList.setLayoutManager(llm);

        // Get the application instance
        Application app = getActivity().getApplication();
        mParisWeatherApp = (ParisWeatherApplication)app;

        initConnectionListener();

        initWeatherReport();

        return v;
    }

    private void initConnectionListener() {
        ParisWeatherActivity act = (ParisWeatherActivity) getActivity();
        act.setConnectionListener(this);
    }

    /**
     * Init weather report.
     */
    private void initWeatherReport(){

        // Init progress dialog
        String dialogTitle = getResources().getString(R.string.loading_data_title);
        String dialogMessage = getResources().getString(R.string.loading_data_message);
        mProgressDialog = new ProgressDialogWrapper(getActivity(), dialogTitle, dialogMessage);

        // In online mode
        if (ConnectionUtils.isConnected(getActivity())) {

            mProgressDialog.showDialog();

            initWeatherReportService();

            ParisWeatherReportImpl weatherReport = new ParisWeatherReportImpl();
            weatherReport.getWeatherReportInstance().getWeatherReportDefault(this);

            return;
        }

        // In offline mode
        boolean noWeatherParisDataExist = mParisWeatherApp
                .getManagerDAO()
                .isWeatherReportEmpty();

        // In case data is exist on local storage
        if (!noWeatherParisDataExist) {
            loadOfflineWeatherReport();
            return;
        }

        mProgressDialog.dismissDialog();
    }

    /**
     * Initialize Weather Report Service by language:
     * E.g: if the current language of device is on french ,
     * we send request with flag proprety which contain "lang=fr"
     */
    private void initWeatherReportService() {

        boolean isFrenchCurrentLanguage = Locale.getDefault().getDisplayLanguage().startsWith("fr");
        ParisWeatherReportImpl weatherReport = new ParisWeatherReportImpl();

        if(isFrenchCurrentLanguage){

            weatherReport.getWeatherReportInstance().getWeatherReportFrench(this);

        }else{

            weatherReport.getWeatherReportInstance().getWeatherReportDefault(this);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        initWeatherReportService();
    }

    /**
     * Load weather report data from local storage on offline mode.
     */
    private void loadOfflineWeatherReport() {

        resetWeatherData();

        ModelWeatherReport weatherReport = mParisWeatherApp
                                            .getManagerDAO()
                                            .getGetModelWeatherReport();

        List<ListWeather> listWR = weatherReport.getListWeather();

        setEmptyMessageVisibility(listWR);

        ParisWeatherAdapter ca = new ParisWeatherAdapter(getActivity(), listWR);

        mRecyclerViewList.setAdapter(ca);

        mProgressDialog.dismissDialog();
    }

    /**
     * Clear data before loading data on offline mode.
     */
    public void resetWeatherData() {

        List<ListWeather> listWR = new ArrayList<>();
        listWR.clear();

        ParisWeatherAdapter ca = new ParisWeatherAdapter(getActivity(), listWR);

        mRecyclerViewList.setAdapter(ca);
        ca.notifyDataSetChanged();
    }

    /**
     * Setter visibility of message when the list is empty.
     * @param listWR current displayed list.
     */
    private void setEmptyMessageVisibility(List<ListWeather> listWR) {

        if (listWR.isEmpty()) {
            mEmptyMessage.setVisibility(View.VISIBLE);
        } else {
            mEmptyMessage.setVisibility(View.GONE);
        }

    }

    @Override
    public void success(ModelWeatherReport modelWeatherReport, Response response) {

        mParisWeatherApp.getManagerDAO().saveWeatherReportCache(modelWeatherReport);

        List<ListWeather> li = modelWeatherReport.getListWeather();

        setEmptyMessageVisibility(li);

        ParisWeatherAdapter ca = new ParisWeatherAdapter(getActivity(), li);

        mRecyclerViewList.setAdapter(ca);

        mProgressDialog.dismissDialog();
    }

    @Override
    public void failure(RetrofitError error) {

        mProgressDialog.dismissDialog();

        String errorDialogTitle = getResources().getString(R.string.error_dialog_title);

        mDialog = new DialogWrapper(getActivity(), errorDialogTitle, error.getMessage());

        mDialog.showDialog();

        Log.e(TAG, "Error occurred during receiving" + error.getResponse().getReason());
    }

    @Override
    public void onConnectionChanged(Intent intent) {

        boolean noWeatherParisDataExist = mParisWeatherApp
                .getManagerDAO()
                .isWeatherReportEmpty();

        String action = intent.getAction();

        boolean isConnected = ConnectionUtils.isConnected(getActivity());

        boolean isEmptyList = noWeatherParisDataExist;

        boolean isConnectionChanged = CONNECTIVITY_CHANGE_ACTION.equals(action);

        if (isConnected && isConnectionChanged && isEmptyList)
        {
            mProgressDialog.showDialog();

            ParisWeatherReportImpl weatherReport = new ParisWeatherReportImpl();

            weatherReport.getWeatherReportInstance().getWeatherReportDefault(this);
        }
    }

}
