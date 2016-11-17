package com.kukuhsain.simple.weather.model.pojo;

import io.realm.RealmObject;

/**
 * Created by kukuh on 14/11/16.
 */

public class Weather extends RealmObject {
    private long sampleId;
    private String name;
    private String description;

    public Weather() {
    }

    public Weather(long sampleId, String name, String description) {
        this.sampleId = sampleId;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSampleId() {
        return sampleId;
    }

    public void setSampleId(long sampleId) {
        this.sampleId = sampleId;
    }
}
