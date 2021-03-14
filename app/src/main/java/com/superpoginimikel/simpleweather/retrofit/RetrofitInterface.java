package com.superpoginimikel.simpleweather.retrofit;

import com.superpoginimikel.simpleweather.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    public static String APP_ID = "95d190a434083879a6398aafd54d9e73";
    public static String UNITS = "metric";

    @GET("/data/2.5/weather")
    Call<WeatherModel> getCurrentWeatherByLocation(@Query("q") String weather,
                                    @Query("appid") String appId,
                                    @Query("units") String units);
}
