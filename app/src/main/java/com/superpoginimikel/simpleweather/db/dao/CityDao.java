package com.superpoginimikel.simpleweather.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.superpoginimikel.simpleweather.db.entity.CityEntity;

import java.util.List;

@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CityEntity city);

    @Delete
    void deleteCity(CityEntity city);

    @Query("SELECT * from city_table")
    LiveData<List<CityEntity>> getAllCities();
}
