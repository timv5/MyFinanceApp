package com.example.myfinanceapp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myfinanceapp.network.YahooFinanceApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import yahoofinance.Stock;

public class YahooFinanceRepository {

    private static final String TAG = "YahooFinanceRepository";

    private Application application;

    public YahooFinanceRepository(Application application) {
        this.application = application;
    }

    public Stock getStockByName(String stockName) {
        Stock s = null;
        try {
            s = YahooFinanceApi.getStockByName(stockName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    public List<Stock> getStocks(String[] stocks) {
        ArrayList<Stock> list = null;
        try {
            Map<String, Stock> map = YahooFinanceApi.getStocks(stocks);
            list = new ArrayList<Stock>(map.values());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
