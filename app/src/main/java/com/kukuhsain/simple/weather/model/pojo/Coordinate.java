package com.kukuhsain.simple.weather.model.pojo;

/**
 * Created by kukuh on 17/11/16.
 */

public class Coordinate {
    private float lat;
    private float lon;

    public Coordinate(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }
}
