package com.example.myfinanceapp.database.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myfinanceapp.database.dao.CryptoCoinDao;
import com.example.myfinanceapp.database.model.CryptoCoin;

@Database(entities = {
        CryptoCoin.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "my_finance_db";
    private static AppDatabase instance;

    public abstract CryptoCoinDao cryptoCoinDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
