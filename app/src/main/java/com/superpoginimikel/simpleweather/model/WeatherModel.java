package com.superpoginimikel.simpleweather.model;

import com.google.gson.annotations.SerializedName;
import com.superpoginimikel.simpleweather.Utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherModel {
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("dt")
    long dateTime;

    @SerializedName("main")
    WeatherModelMain main;

    @SerializedName("sys")
    WeatherModelSys sys;

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d Y hh:mm a");
        return formatter.format(new Date(dateTime * 1000));
    }
    public WeatherModelMain getMain() {
        return main;
    }
    public WeatherModelSys getSys() {
        return sys;
    }

    public class WeatherModelMain {
        @SerializedName("temp")
        Float temp;

        @SerializedName("feels_like")
        Float feelsLike;

        @SerializedName("temp_min")
        Float tempMin;

        @SerializedName("temp_max")
        Float tempMax;

        @SerializedName("humidity")
        Float humidity;

        public Integer getTemp() {
            return Math.round(temp);
        }
        public Integer getFeelsLike() {
            return Math.round(feelsLike);
        }
        public Integer getTempMin() {
            return Math.round(tempMin);
        }
        public Integer getTempMax() {
            return Math.round(tempMax);
        }
        public Integer getHumidity() {
            return Math.round(humidity);
        }
    }

    public class WeatherModelSys {
        @SerializedName("sunrise")
        long sunrise;

        @SerializedName("sunset")
        long sunset;

        public String getSunrise() {
            return DateUtils.ConvertLongToTimeFormat(sunrise);
        }

        public String getSunset() {
            return DateUtils.ConvertLongToTimeFormat(sunset);
        }
    }
}