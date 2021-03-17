package com.superpoginimikel.simpleweather;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.superpoginimikel.simpleweather.db.CityRoomDatabase;
import com.superpoginimikel.simpleweather.db.dao.CityDao;
import com.superpoginimikel.simpleweather.db.entity.CityEntity;

import java.util.List;

public class CityRepository {
    private CityDao cityDao;
    private LiveData<List<CityEntity>> allCities;

    CityRepository(Application application) {
        CityRoomDatabase db = CityRoomDatabase.getDatabase(application);
        cityDao = db.cityDao();
        allCities = cityDao.getAllCities();
    }

    LiveData<List<CityEntity>> getAllCities() {
        return allCities;
    }

    public void insert(CityEntity word) {
        new insertAsyncTask(cityDao).execute(word);
    }

    // Need to run off main thread
    public void deleteCity(CityEntity word) {
        new deleteCityAsyncTask(cityDao).execute(word);
    }

    // Static inner classes below here to run database interactions
    // in the background.

    /**
     * Insert a word into the database.
     */
    private static class insertAsyncTask extends AsyncTask<CityEntity, Void, Void> {

        private CityDao mAsyncTaskDao;

        insertAsyncTask(CityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CityEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     *  Delete a single word from the database.
     */
    private static class deleteCityAsyncTask extends AsyncTask<CityEntity, Void, Void> {
        private CityDao mAsyncTaskDao;

        deleteCityAsyncTask(CityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CityEntity... params) {
            mAsyncTaskDao.deleteCity(params[0]);
            return null;
        }
    }
}
