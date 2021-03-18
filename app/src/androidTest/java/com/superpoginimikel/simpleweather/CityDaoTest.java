package com.superpoginimikel.simpleweather;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.superpoginimikel.simpleweather.db.CityRoomDatabase;
import com.superpoginimikel.simpleweather.db.dao.CityDao;
import com.superpoginimikel.simpleweather.db.entity.CityEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CityDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private CityRoomDatabase cityRoomDatabase;

    private CityDao cityDao;

    @Before
    public void initDb() throws Exception {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        cityRoomDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                CityRoomDatabase.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();

        cityDao = cityRoomDatabase.cityDao();
    }

    @After
    public void closeDb() throws Exception {
        cityRoomDatabase.close();
    }

    @Test
    public void getCitiesWhenNoneInserted() throws InterruptedException {
        List<CityEntity> comments = LiveDataTestUtil.getValue(cityDao.getAllCities());

        assertTrue(comments.isEmpty());
    }

}
