package com.superpoginimikel.simpleweather.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_table")
public class CityEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "city")
    private String city;

    public CityEntity(@NonNull String city) {this.city = city;}

    public String getCity(){return this.city;}
}
