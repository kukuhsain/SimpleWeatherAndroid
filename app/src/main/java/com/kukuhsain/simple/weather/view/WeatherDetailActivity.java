package com.kukuhsain.simple.weather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kukuhsain.simple.weather.R;
import com.kukuhsain.simple.weather.model.pojo.Weather;

import timber.log.Timber;

/**
 * Created by kukuh on 17/11/16.
 */

public class WeatherDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        String weatherString = getIntent().getStringExtra("weather");
        Weather weather = (new Gson()).fromJson(weatherString, Weather.class);

        Timber.d("weather received...");
        Timber.d(weather.getName());
    }
}
