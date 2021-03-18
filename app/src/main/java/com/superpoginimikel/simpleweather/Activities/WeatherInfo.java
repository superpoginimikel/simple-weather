package com.superpoginimikel.simpleweather.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.superpoginimikel.simpleweather.R;
import com.superpoginimikel.simpleweather.databinding.WeatherInfoBinding;
import com.superpoginimikel.simpleweather.model.WeatherModel;
import com.superpoginimikel.simpleweather.retrofit.RetrofitInstance;
import com.superpoginimikel.simpleweather.retrofit.RetrofitInterface;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherInfo extends AppCompatActivity {

    private CardView temperatureCardView;
    private CardView sunInfoCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_info);

        temperatureCardView = findViewById(R.id.temperature_card);
        sunInfoCardView = findViewById(R.id.sun_info_card);

        Intent intent = getIntent();
        String city = intent.getStringExtra(MainActivity.EXTRA_CITY);

        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        Call<WeatherModel> weatherCall = retrofitInterface.getCurrentWeatherByLocation(city, RetrofitInterface.APP_ID, RetrofitInterface.UNITS);
        Log.d("Url", weatherCall.request().url().toString());

        // TODO: Show loading until data comes back
        // For the mean time, hide all views until we get a result
        /*
            Loading here or pre-made layout for loading
        */

        weatherCall.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.body() != null)
                {
                    WeatherModel weather = response.body();
                    WeatherInfoBinding binding = DataBindingUtil.setContentView(WeatherInfo.this, R.layout.weather_info);
                    binding.setWeather(weather);
                }
                else
                {
                    FailedWeatherInfo();
                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                // Throw and handle error here
                FailedWeatherInfo();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            SharedPreferences preferences = getSharedPreferences(MainActivity.sharedPrefFile, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(String.valueOf(R.string.last_city_searched), "");
            editor.apply();
            onBackPressed();
        }
        return true;
    }

    private void FailedWeatherInfo()
    {
        // TODO: Show city not found error and have a better error page
        // Unless we auto complete cities and let them choose and not freely type
        TextView cityTitle = findViewById(R.id.city_name);
        cityTitle.setText(R.string.city_not_found);
        temperatureCardView.setVisibility(View.GONE);
        sunInfoCardView.setVisibility(View.GONE);
    }
}
