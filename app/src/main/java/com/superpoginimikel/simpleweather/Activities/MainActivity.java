package com.superpoginimikel.simpleweather.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.superpoginimikel.simpleweather.CityListAdapter;
import com.superpoginimikel.simpleweather.CityViewModel;
import com.superpoginimikel.simpleweather.R;
import com.superpoginimikel.simpleweather.db.entity.CityEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "com.superpoginimikel.simpleweather.extra.CITY";

    private EditText searchCityEditText;
    private CityViewModel cityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchCityEditText = findViewById(R.id.searchCityEditText);

        // Setup the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CityListAdapter adapter = new CityListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup the WordViewModel
        cityViewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        // Get all the words from the database
        // and associate them to the adapter
        cityViewModel.getAllCities().observe(this, new Observer<List<CityEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CityEntity> cities) {
                // Update the cached copy of the words in the adapter.
                adapter.setCities(cities);
            }
        });
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
