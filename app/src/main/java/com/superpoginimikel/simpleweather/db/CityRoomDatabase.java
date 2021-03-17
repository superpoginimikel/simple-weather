package com.superpoginimikel.simpleweather.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.superpoginimikel.simpleweather.db.dao.CityDao;
import com.superpoginimikel.simpleweather.db.entity.CityEntity;

@Database(entities = {CityEntity.class}, version = 1, exportSchema = false)
public abstract class CityRoomDatabase extends RoomDatabase {

    public abstract CityDao cityDao();

    private static CityRoomDatabase INSTANCE;

    public static CityRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CityRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CityRoomDatabase.class, "city_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
