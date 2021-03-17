package com.superpoginimikel.simpleweather;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.superpoginimikel.simpleweather.db.entity.CityEntity;

import java.util.List;

public class CityViewModel extends AndroidViewModel {

    private CityRepository mRepository;

    private LiveData<List<CityEntity>> allCities;

    public CityViewModel(Application application) {
        super(application);
        mRepository = new CityRepository(application);
        allCities = mRepository.getAllCities();
    }

    public LiveData<List<CityEntity>> getAllCities() {
        return allCities;
    }

    public void insert(CityEntity city) {
        mRepository.insert(city);
    }

    public void deleteCity(CityEntity city) {
        mRepository.deleteCity(city);
    }
}

