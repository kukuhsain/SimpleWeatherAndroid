package com.kukuhsain.simple.weather.model.remote;

import com.kukuhsain.simple.weather.model.local.PreferencesHelper;
import com.kukuhsain.simple.weather.model.pojo.ForecastResult;

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

    public Observable<ForecastResult> getForecastByCity(String city) {
        return api.getForecastByCity(city, "json", "metric", "16", OPENWEATHER_APP_ID);
    }

    private interface ApiEndpoint {
        @GET("/data/2.5/forecast/daily")
        Observable<ForecastResult> getForecastByCity(@Query("q") String city,
                                                     @Query("mode") String mode,
                                                     @Query("units") String units,
                                                     @Query("cnt") String cnt,
                                                     @Query("appid") String appId);
    }
}
