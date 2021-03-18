package com.superpoginimikel.simpleweather.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.superpoginimikel.simpleweather.CityClickCallback;
import com.superpoginimikel.simpleweather.CityListAdapter;
import com.superpoginimikel.simpleweather.CityViewModel;
import com.superpoginimikel.simpleweather.R;
import com.superpoginimikel.simpleweather.db.entity.CityEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "com.superpoginimikel.simpleweather.extra.CITY";
    public static final String sharedPrefFile = "com.superpoginimikel.simpleweather";

    private SharedPreferences preferences;

    private EditText searchCityEditText;
    private CityViewModel cityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String lastCitySearched = preferences.getString(String.valueOf(R.string.last_city_searched), "default string");
        if (lastCitySearched != "")
        {
            StartWeatherInfoActivity(lastCitySearched);
        }

        setContentView(R.layout.activity_main);
        searchCityEditText = findViewById(R.id.searchCityEditText);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CityListAdapter adapter = new CityListAdapter(this, cityClickCallback);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(
                ContextCompat.getDrawable(getBaseContext(), R.drawable.divider)
        );
        recyclerView.addItemDecoration(divider);

        cityViewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        cityViewModel.getAllCities().observe(this, new Observer<List<CityEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CityEntity> cities) {
                adapter.setCities(cities);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        CityEntity city = adapter.getWordAtPosition(position);
                        cityViewModel.deleteCity(city);
                    }
                });
        helper.attachToRecyclerView(recyclerView);
    }

    private final CityClickCallback cityClickCallback = cityEntity -> {
        String city = cityEntity.getCity();
        StartWeatherInfoActivity(city);
    };

    public void searchCity(View view) {
        if (!TextUtils.isEmpty(searchCityEditText.getText())) {
            String city = searchCityEditText.getText().toString();

            InsertCityToDb(city);
            StartWeatherInfoActivity(city);
        }
    }

    private void StartWeatherInfoActivity(String city)
    {
        Intent cityInfoIntent = new Intent(this, WeatherInfo.class);
        cityInfoIntent.putExtra(EXTRA_CITY, city);
        startActivity(cityInfoIntent);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(String.valueOf(R.string.last_city_searched), city);
        editor.apply();
    }

    private void InsertCityToDb(String city)
    {
        CityEntity cityEntity = new CityEntity(city);
        cityViewModel.insert(cityEntity);
    }
}
