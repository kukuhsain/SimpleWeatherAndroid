package com.kukuhsain.simple.weather.model.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukuh on 17/11/16.
 */

public class ForecastResult {
    private City city;
    private String cod;
    private float message;
    private int cnt;
    private List<Forecast> list = new ArrayList<Forecast>();

    public ForecastResult(City city, String cod, float message, int cnt, List<Forecast> list) {
        this.city = city;
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public String getCod() {
        return cod;
    }

    public float getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<Forecast> getList() {
        return list;
    }
}
