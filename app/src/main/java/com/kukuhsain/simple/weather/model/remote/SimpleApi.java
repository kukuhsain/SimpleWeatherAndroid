package com.kukuhsain.simple.weather.model.remote;

import com.google.gson.JsonObject;
import com.kukuhsain.simple.weather.model.local.PreferencesHelper;
import com.kukuhsain.simple.weather.model.pojo.Weather;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kukuh on 15/10/16.
 */

public class SimpleApi {
    public static final String BASE_URL = "http://api.openweathermap.org";
    private final String OPENWEATHER_APP_ID = "cef209f6f46bb527dba385b81b808ce9";
    private static SimpleApi INSTANCE;
    private ApiEndpoint api;
    private static String accessToken;

    private SimpleApi() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        api = retrofit.create(ApiEndpoint.class);
    }

    public static SimpleApi getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleApi();
        }
        accessToken = PreferencesHelper.getInstance().getAccessToken();
        return INSTANCE;
    }

    public Observable<Weather> getWeatherByCity(String city) {
        return api.getWeatherByCity(city, "metric", OPENWEATHER_APP_ID).map(jsonObject -> {
            Weather weather = new Weather();
            JsonObject weatherObject = jsonObject.get("weather").getAsJsonArray()
                    .get(0).getAsJsonObject();
            JsonObject weatherWind = jsonObject.get("wind").getAsJsonObject();
            JsonObject weatherSys = jsonObject.get("sys").getAsJsonObject();
            JsonObject weatherMain = jsonObject.get("main").getAsJsonObject();
            JsonObject weatherCoord = jsonObject.get("coord").getAsJsonObject();

            weather.setName(weatherObject.get("main").getAsString());
            weather.setDescription(weatherObject.get("description").getAsString());
            weather.setIcon(weatherObject.get("icon").getAsString());

            weather.setRequestDate(new Date(jsonObject.get("dt").getAsLong()*1000));
            weather.setSunriseDate(new Date(weatherSys.get("sunrise").getAsLong()*1000));
            weather.setSunsetDate(new Date(weatherSys.get("sunset").getAsLong()*1000));

            weather.setCity(jsonObject.get("name").getAsString());
            weather.setCountryId(weatherSys.get("country").getAsString());
            weather.setLatitude(weatherCoord.get("lat").getAsDouble());
            weather.setLongitude(weatherCoord.get("lon").getAsDouble());

            weather.setTemperature(weatherMain.get("temp").getAsFloat());
            weather.setHumidity(weatherMain.get("humidity").getAsFloat());
            weather.setPressure(weatherMain.get("pressure").getAsFloat());
            weather.setCloudiness(jsonObject.get("clouds").getAsJsonObject()
                    .get("all").getAsFloat());
            weather.setWindSpeed(weatherWind.get("speed").getAsFloat());
            weather.setWindDegree(weatherWind.get("deg").getAsFloat());

            return weather;
        });
    }

    private interface ApiEndpoint {
        @GET("/data/2.5/weather")
        Observable<JsonObject> getWeatherByCity(@Query("q") String city,
                                                @Query("units") String units,
                                                @Query("appid") String appId);
    }
}
