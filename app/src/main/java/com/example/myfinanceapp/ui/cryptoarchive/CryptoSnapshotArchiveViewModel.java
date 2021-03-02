package com.example.myfinanceapp.ui.cryptoarchive;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myfinanceapp.database.model.CryptoCoin;
import com.example.myfinanceapp.repository.CryptoCoinRepository;

import java.util.List;

public class CryptoSnapshotArchiveViewModel extends AndroidViewModel {

    private CryptoCoinRepository cryptoCoinRepository;
    private LiveData<List<CryptoCoin>> cryptoCoins;

    public CryptoSnapshotArchiveViewModel(@NonNull Application application) {
        super(application);
        this.cryptoCoinRepository = new CryptoCoinRepository(application);
        cryptoCoins = this.cryptoCoinRepository.getAll();
    }

    public LiveData<List<CryptoCoin>> getCryptoCoins() {
        return cryptoCoins;
    }

    public void delete(CryptoCoin cryptoCoin) {
        this.cryptoCoinRepository.delete(cryptoCoin);
    }
}