package com.kukuhsain.simple.weather.model.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukuh on 17/11/16.
 */

public class Forecast {
    private int dt;
    private Temperature temp;
    private float pressure;
    private int humidity;
    private List<Weather> weather = new ArrayList<Weather>();
    private float speed;
    private int deg;
    private int clouds;
    private float rain;

    public Forecast(int dt, Temperature temp, float pressure, int humidity, List<Weather> weather, float speed, int deg, int clouds, float rain) {
        this.dt = dt;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
        this.speed = speed;
        this.deg = deg;
        this.clouds = clouds;
        this.rain = rain;
    }

    public int getDt() {
        return dt;
    }

    public Temperature getTemp() {
        return temp;
    }

    public float getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public float getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }

    public int getClouds() {
        return clouds;
    }

    public float getRain() {
        return rain;
    }
}
