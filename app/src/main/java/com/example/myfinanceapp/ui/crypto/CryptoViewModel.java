package com.example.myfinanceapp.ui.crypto;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.myfinanceapp.database.model.CryptoCoin;
import com.example.myfinanceapp.network.APIClient;
import com.example.myfinanceapp.network.CryptoAPI;
import com.example.myfinanceapp.network.dto.Market;
import com.example.myfinanceapp.repository.CryptoCoinRepository;
import com.example.myfinanceapp.util.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoViewModel extends AndroidViewModel {

    private final CryptoAPI cryptoAPI;
    private CryptoCoinRepository cryptoCoinRepository;

    public CryptoViewModel(@NonNull Application application) {
        super(application);
        this.cryptoAPI = APIClient.getClient().create(CryptoAPI.class);
        cryptoCoinRepository = new CryptoCoinRepository(application);
    }

    public Call<List<Market>> getCryptoMarket() {
        Map<String, String> params = new HashMap<>();
        params.put("x-rapidapi-key", Constants.CRYPTO_HEADER_KEY);
        params.put("x-rapidapi-host", Constants.CRYPTO_HEADER_HOST);
        return this.cryptoAPI.getCryptoMarket(params);
    }

    public void insert(CryptoCoin cryptoCoin) {
        this.cryptoCoinRepository.insert(cryptoCoin);
    }

}