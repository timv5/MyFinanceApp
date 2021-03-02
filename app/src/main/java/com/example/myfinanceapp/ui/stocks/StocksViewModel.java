package com.example.myfinanceapp.ui.stocks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myfinanceapp.repository.YahooFinanceRepository;
import com.example.myfinanceapp.util.Constants;

import java.util.List;

import yahoofinance.Stock;

public class StocksViewModel extends AndroidViewModel {

    private final YahooFinanceRepository yahooFinanceRepository;

    public StocksViewModel(@NonNull Application application) {
        super(application);
        yahooFinanceRepository = new YahooFinanceRepository(application);
    }

    public Stock getStockByName(String stockName) {
        return this.yahooFinanceRepository.getStockByName(stockName);
    }

    public List<Stock> getStocks() {
        return yahooFinanceRepository.getStocks(Constants.STOCK_LIST);
    }
}