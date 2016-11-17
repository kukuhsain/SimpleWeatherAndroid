package com.kukuhsain.simple.weather.model.pojo;

/**
 * Created by kukuh on 14/11/16.
 */

public class Weather {
    private String name;
    private String description;
    private String icon;

    private String city;
    private String countryId;

    private float cloudiness;
    private float pressure;
    private float humidity;

    private double latitude;
    private double longitude;

    public Weather(String name, String description, String icon, String city, String countryId, float cloudiness, float pressure, float humidity, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.city = city;
        this.countryId = countryId;
        this.cloudiness = cloudiness;
        this.pressure = pressure;
        this.humidity = humidity;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getCity() {
        return city;
    }

    public String getCountryId() {
        return countryId;
    }

    public float getCloudiness() {
        return cloudiness;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
