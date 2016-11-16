package com.kukuhsain.simple.weather.model.pojo;

/**
 * Created by kukuh on 17/11/16.
 */

public class City {
    private int id;
    private String name;
    private Coordinate coord;
    private String country;
    private int population;

    public City(int id, String name, Coordinate coord, String country, int population) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.country = country;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }
}
