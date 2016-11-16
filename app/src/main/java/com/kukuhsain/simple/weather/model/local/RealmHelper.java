package com.kukuhsain.simple.weather.model.local;

import com.kukuhsain.simple.weather.model.pojo.Weather;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kukuh on 04/11/16.
 */

public class RealmHelper {
    private static RealmHelper INSTANCE;

    private RealmHelper() {}

    public static RealmHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RealmHelper();
        }
        return INSTANCE;
    }

    public void addWeather(Weather weather) {
        List<Weather> weathers = getAllWeathers();
        boolean isExisted = false;
        for (Weather weather1 : weathers) {
            if (weather.getSampleId() == weather1.getSampleId()) {
                isExisted = true;
            }
        }
        if (!isExisted) {
            Realm.getDefaultInstance().executeTransaction(realm -> {
                realm.copyToRealm(weather);
            });
        }
    }

    public void addWeathers(List<Weather> weathers) {
        List<Weather> realmWeathers = getAllWeathers();
        for (Weather weather : weathers) {
            boolean isExisted = false;
            for (Weather realmWeather : realmWeathers) {
                if (realmWeather.getSampleId() == weather.getSampleId()) {
                    isExisted = true;
                }
            }
            if (!isExisted) {
                Realm.getDefaultInstance().executeTransaction(realm -> {
                    realm.copyToRealm(weather);
                });
            }
        }
    }

    public List<Weather> getAllWeathers() {
        RealmResults<Weather> iterableWeathers = Realm.getDefaultInstance()
                .where(Weather.class).findAll();
        return Realm.getDefaultInstance().copyFromRealm(iterableWeathers);
    }
}
