package com.kukuhsain.simple.weather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kukuhsain.simple.weather.R;
import com.kukuhsain.simple.weather.model.pojo.Weather;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukuh on 17/11/16.
 */

public class WeatherDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_location) TextView tvLocation;
    @BindView(R.id.iv_icon) ImageView ivIcon;
    @BindView(R.id.tv_temperature) TextView tvTemperature;
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_description) TextView tvDescription;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_wind) TextView tvWind;
    @BindView(R.id.tv_cloudiness) TextView tvCloudiness;
    @BindView(R.id.tv_pressure) TextView tvPressure;
    @BindView(R.id.tv_humidity) TextView tvHumidity;
    @BindView(R.id.tv_sunrise) TextView tvSunrise;
    @BindView(R.id.tv_sunset) TextView tvSunset;
    @BindView(R.id.tv_coord) TextView tvCoord;

    private Weather weather;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Weather In Your City");

        String weatherString = getIntent().getStringExtra("weather");
        weather = (new Gson()).fromJson(weatherString, Weather.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (weather != null) {
            tvLocation.setText(weather.getCity()+", "+weather.getCountryId());
            tvTemperature.setText(weather.getTemperature()+(char)0x00B0+"C");
            tvName.setText(weather.getName());
            tvDescription.setText(weather.getDescription());
            tvDate.setText("get at "+new SimpleDateFormat("yyyy.MM.dd HH:mm").format(
                    weather.getRequestDate()
            ));
            tvWind.setText("Speed "+weather.getWindSpeed()+" m/s with direction to "+weather.getWindDegree()+" degree");
            tvCloudiness.setText(weather.getCloudiness()+" %");
            tvPressure.setText(weather.getPressure()+" hPa");
            tvHumidity.setText(weather.getHumidity()+" %");
            tvSunrise.setText(new SimpleDateFormat("HH:mm").format(weather.getSunriseDate()));
            tvSunset.setText(new SimpleDateFormat("HH:mm").format(weather.getSunsetDate()));
            tvCoord.setText("[ "+weather.getLatitude()+", "+weather.getLongitude()+" ]");
        }
    }
}
