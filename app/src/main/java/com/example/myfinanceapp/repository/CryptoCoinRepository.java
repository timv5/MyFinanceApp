package com.example.myfinanceapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myfinanceapp.database.dao.CryptoCoinDao;
import com.example.myfinanceapp.database.db.AppDatabase;
import com.example.myfinanceapp.database.model.CryptoCoin;

import java.util.List;

public class CryptoCoinRepository {

    private final CryptoCoinDao cryptoCoinDao;

    public CryptoCoinRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        this.cryptoCoinDao = database.cryptoCoinDao();
    }

    public void delete(CryptoCoin cryptoCoin) {
        this.cryptoCoinDao.delete(cryptoCoin);
    }

    public void update(CryptoCoin cryptoCoin) {
        this.cryptoCoinDao.update(cryptoCoin);
    }

    public void insert(CryptoCoin cryptoCoin) {
        this.cryptoCoinDao.insert(cryptoCoin);
    }

    public LiveData<List<CryptoCoin>> getAll() {
        return this.cryptoCoinDao.getAll();
    }

}
