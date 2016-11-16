package com.kukuhsain.simple.weather.model.pojo;

/**
 * Created by kukuh on 17/11/16.
 */

public class Temperature {
    private float day;
    private float min;
    private float max;
    private float night;
    private float eve;
    private float morn;

    public Temperature(float day, float min, float max, float night, float eve, float morn) {
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    public float getDay() {
        return day;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getNight() {
        return night;
    }

    public float getEve() {
        return eve;
    }

    public float getMorn() {
        return morn;
    }
}
