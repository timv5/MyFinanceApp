package com.example.myfinanceapp.network;

import com.example.myfinanceapp.network.dto.Market;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface CryptoAPI {

    @GET("/coins/markets?vs_currency=usd&page=1&per_page=10&order=market_cap_desc")
    Call<List<Market>> getCryptoMarket(@HeaderMap Map<String, String> headers);

}
