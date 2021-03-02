package com.example.myfinanceapp.network;

import java.io.IOException;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class YahooFinanceApi {

    public static Stock getStockByName(String stockName) throws IOException {
        return YahooFinance.get(stockName);
    }

    public static Map<String, Stock> getStocks(String[] stocks) throws IOException {
        return YahooFinance.get(stocks);
    }

}
