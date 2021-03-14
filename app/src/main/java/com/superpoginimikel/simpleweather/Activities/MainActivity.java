package com.superpoginimikel.simpleweather.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.superpoginimikel.simpleweather.R;
import com.superpoginimikel.simpleweather.databinding.ActivityMainBinding;
import com.superpoginimikel.simpleweather.model.WeatherModel;
import com.superpoginimikel.simpleweather.retrofit.RetrofitInstance;
import com.superpoginimikel.simpleweather.retrofit.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        Call<WeatherModel> weatherCall = retrofitInterface.getCurrentWeatherByLocation("Japan", RetrofitInterface.APP_ID, RetrofitInterface.UNITS);

        // Show loading until data comes back
        /*
            Loading here or pre-made layout for loading
        */

        weatherCall.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                Log.d("Response:", response.body().toString());

                WeatherModel weather = response.body();
                ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
                binding.setWeather(weather);
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                // Throw and handle error here
            }
        });
    }
}
