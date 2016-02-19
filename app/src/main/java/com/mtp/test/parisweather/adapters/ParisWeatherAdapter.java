package com.mtp.test.parisweather.adapters;

import static com.mtp.test.parisweather.utils.ParisWeatherConstants.ICON_EXTENSION;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.ICON_URL;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.ITEM_PARIS_WEATHER_KEY;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.PERCENT;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.UNIT_RAIN;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.UNIT_TEMP_CELSIUS;
import static com.mtp.test.parisweather.utils.ParisWeatherConstants.UNIT_WIND_SPEED;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtp.test.parisweather.R;
import com.mtp.test.parisweather.activities.ParisWeatherDetailsActivity;
import com.mtp.test.parisweather.models.weather_reports.ListWeather;
import com.mtp.test.parisweather.models.weather_reports.Temperature;
import com.mtp.test.parisweather.models.weather_reports.Weather;
import com.mtp.test.parisweather.utils.ConnectionUtils;
import com.mtp.test.parisweather.utils.DateUtils;
import com.mtp.test.parisweather.utils.FileUtils;
import com.mtp.test.parisweather.utils.TextUtils;
import com.mtp.test.parisweather.utils.WeatherUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.util.List;

public class ParisWeatherAdapter extends RecyclerView.Adapter<ParisWeatherAdapter.ContactViewHolder> implements View.OnClickListener {

    private Context context;
    private List<ListWeather> listWeather;
    private String icon;

    public ParisWeatherAdapter(Context context, List<ListWeather> listWeather) {
        this.listWeather = listWeather;
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return listWeather.size();
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder weatherViewHolder, int i) {

        ListWeather weatherDescription = listWeather.get(i);

        // Get current date of weather
        long date = weatherDescription.getDate();
        String formattedDate = DateUtils.convertDate(date);

        // Get current state of humidity
        int humidity = weatherDescription.getHumidity();

        // Get current state of rain
        float rain = weatherDescription.getRain();

        // Get weather bean
        Weather w = listWeather.get(i).getWeather().get(0);

        // Get current temperature
        Temperature temperature = weatherDescription.getTemperature();
        float dayTemp = temperature.getDay();

        // Get speed wind weather
        float sp = weatherDescription.getSpeed();
        int spKmHours = WeatherUtils.convertSpeedWind(sp);

        weatherViewHolder.date.setText(TextUtils.capitalizeFirstChar(formattedDate));
        weatherViewHolder.humidity.setText(humidity + PERCENT);
        weatherViewHolder.rain.setText(String.valueOf(rain) + UNIT_RAIN);
        weatherViewHolder.temperature.setText(String.valueOf((int) dayTemp) + UNIT_TEMP_CELSIUS);
        weatherViewHolder.weather.setText(w.getMain());
        weatherViewHolder.weatherDescription.setText("(" + w.getDescription() + ")");
        weatherViewHolder.speed.setText(String.valueOf(spKmHours) +  UNIT_WIND_SPEED);
        weatherViewHolder.more_details.setOnClickListener(this);
        weatherViewHolder.more_details.setTag((i));

        //Loading weather icon
        icon = w.getIcon();
        String urlIcon = getUrlIcon(icon);

        loadWeatherIcon(weatherViewHolder.weatherIcon ,urlIcon);
    }

    /**
     * Load weather icon.
     *
     * @param urlIcon url icon.
     * @param imageIconView image view icon.
     */
    private void loadWeatherIcon(final ImageView imageIconView, String urlIcon) {

        boolean isConnected = ConnectionUtils.isConnected(context);
        Uri uriImg = accessToIconURI(icon);
        boolean isImgExist = FileUtils.isExistFile(uriImg.getPath());

        if(isConnected && !isImgExist){

            Target target = new Target() {
                @Override
                public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {

                    imageIconView.setImageBitmap(bitmap);

                    FileUtils.saveImage(icon, bitmap);

                }
                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    // Ignore
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    // Ignore
                }
            };

            int widthInPixels = (int) context.getResources().getDimension(R.dimen.width_icon_weather);
            int heightInPixels = (int) context.getResources().getDimension(R.dimen.height_icon_weather);

            // Display and Save image icon
            Picasso.with(context)
                    .load(urlIcon)
                    .placeholder(R.drawable.ic_weather_thumb)
                    .error(R.drawable.ic_weather_thumb)
                    .resizeDimen(R.dimen.card_view_width_image_size, R.dimen.card_view_height_image_size)
                    .resize(widthInPixels, heightInPixels)
                    .into(target);
        }else{

            Uri img = accessToIconURI(icon);

            imageIconView.setImageURI(img);
        }


    }

    /**
     * Concat name icon to url to get icon from server.
     *
     * @param iconName icon name.
     * @return url of icon.
     */
    private String getUrlIcon(String iconName) {
        return ICON_URL + iconName + ICON_EXTENSION;
    }

    /**
     * Access to icon uri.
     * @param mIcon icon path.
     * @return
     */
    private Uri accessToIconURI(String mIcon) {
        String uri = FileUtils.getCacheDirectoryApplication() + File.separator + mIcon + ICON_EXTENSION;
        return Uri.parse(uri);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout_paris_weather, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.more_details){
            Intent intent  = new Intent(context, ParisWeatherDetailsActivity.class);
            int itemId = (int)v.getTag();
            intent.putExtra(ITEM_PARIS_WEATHER_KEY, itemId);
            context.startActivity(intent);
        }
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout more_details;
        private TextView rain;
        private TextView humidity;
        private TextView temperature;
        private TextView weather;
        private TextView date;
        private TextView speed;
        private ImageView weatherIcon;
        private TextView weatherDescription;

        public ContactViewHolder(View v) {
            super(v);
            weatherIcon = (ImageView) v.findViewById(R.id.weatherIcon);
            date = (TextView) v.findViewById(R.id.date);
            weather = (TextView) v.findViewById(R.id.weather);
            weatherDescription = (TextView)v.findViewById(R.id.weather_description);
            temperature = (TextView) v.findViewById(R.id.temperature);
            humidity = (TextView) v.findViewById(R.id.humidity);
            rain = (TextView) v.findViewById(R.id.rain);
            speed = (TextView) v.findViewById(R.id.speed);
            more_details = (LinearLayout)v.findViewById(R.id.more_details);
        }
    }
}
