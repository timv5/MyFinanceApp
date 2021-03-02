package com.example.myfinanceapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfinanceapp.database.model.CryptoCoin;

import java.util.List;

@Dao
public interface CryptoCoinDao {

    @Insert
    void insert(CryptoCoin cryptoCoin);

    @Update
    void update(CryptoCoin cryptoCoin);

    @Delete
    void delete(CryptoCoin cryptoCoin);

    @Query(SqlConstants.GET_CRYPTO_COIN)
    LiveData<List<CryptoCoin>> getAll();

    class SqlConstants {

        private static final String GET_CRYPTO_COIN = "SELECT * from crypto_coin";

    }

}
