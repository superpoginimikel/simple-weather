package com.superpoginimikel.simpleweather.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.superpoginimikel.simpleweather.R;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "com.superpoginimikel.simpleweather.extra.CITY";

    private EditText searchCityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchCityEditText = findViewById(R.id.searchCityEditText);
    }

    public void searchCity(View view) {
        if (!TextUtils.isEmpty(searchCityEditText.getText())) {
            String city = searchCityEditText.getText().toString();
            Intent cityInfoIntent = new Intent(this, WeatherInfo.class);
            cityInfoIntent.putExtra(EXTRA_CITY, city);
            startActivity(cityInfoIntent);
        }
    }
}
