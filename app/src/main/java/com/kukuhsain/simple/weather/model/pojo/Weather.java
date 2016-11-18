package com.kukuhsain.simple.weather.model.pojo;

import java.util.Date;

/**
 * Created by kukuh on 14/11/16.
 */

public class Weather {
    private String name;
    private String description;
    private String icon;

    private String city;
    private String countryId;

    private float temperature;
    private float cloudiness;
    private float pressure;
    private float humidity;

    private float windSpeed;
    private float windDegree;

    private Date requestDate;
    private Date sunriseDate;
    private Date sunsetDate;

    private double latitude;
    private double longitude;

    public Weather() {
    }

    public Weather(String name, String description, String icon, String city, String countryId, float temperature, float cloudiness, float pressure, float humidity, float windSpeed, float windDegree, Date requestDate, Date sunriseDate, Date sunsetDate, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.city = city;
        this.countryId = countryId;
        this.temperature = temperature;
        this.cloudiness = cloudiness;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
        this.requestDate = requestDate;
        this.sunriseDate = sunriseDate;
        this.sunsetDate = sunsetDate;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public float getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(float cloudiness) {
        this.cloudiness = cloudiness;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(float windDegree) {
        this.windDegree = windDegree;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getSunriseDate() {
        return sunriseDate;
    }

    public void setSunriseDate(Date sunriseDate) {
        this.sunriseDate = sunriseDate;
    }

    public Date getSunsetDate() {
        return sunsetDate;
    }

    public void setSunsetDate(Date sunsetDate) {
        this.sunsetDate = sunsetDate;
    }
}
