package com.kukuhsain.simple.weather.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.kukuhsain.simple.weather.R;
import com.kukuhsain.simple.weather.model.pojo.Weather;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by kukuh on 17/11/16.
 */

public class WeatherDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);

        String weatherString = getIntent().getStringExtra("weather");
        Weather weather = (new Gson()).fromJson(weatherString, Weather.class);

        Timber.d("weather received...");
        if (weather != null) {
            Timber.d(weather.getName());
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Weather Detail");
    }
}
