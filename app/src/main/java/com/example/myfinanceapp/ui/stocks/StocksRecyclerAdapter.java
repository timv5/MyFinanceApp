package com.example.myfinanceapp.ui.stocks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinanceapp.R;

import java.util.ArrayList;
import java.util.List;

import yahoofinance.Stock;

public class StocksRecyclerAdapter extends RecyclerView.Adapter<StocksRecyclerAdapter.StocksHolder> {

    private List<Stock> stocks = new ArrayList<>();

    @NonNull
    @Override
    public StocksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.stocks_item, parent, false);
        return new StocksHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StocksHolder holder, int position) {
        Stock stock = this.stocks.get(position);
        holder.textViewCurrencyResult.setText(stock.getCurrency());
        holder.textViewExchangeResult.setText(stock.getStockExchange());
        holder.textViewPriceResult.setText(String.valueOf(stock.getQuote().getPrice()));
        holder.textViewNameResult.setText(stock.getName());
        holder.textViewSymbolResult.setText(stock.getSymbol());
    }

    @Override
    public int getItemCount() {
        return this.stocks.size();
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
        notifyDataSetChanged();
    }

    static class StocksHolder extends RecyclerView.ViewHolder {

        private final TextView textViewSymbolResult;
        private final TextView textViewCurrencyResult;
        private final TextView textViewExchangeResult;
        private final TextView textViewPriceResult;
        private final TextView textViewNameResult;

        public StocksHolder(@NonNull View itemView) {
            super(itemView);
            textViewCurrencyResult = itemView.findViewById(R.id.low_result);
            textViewSymbolResult = itemView.findViewById(R.id.symbol_result);
            textViewExchangeResult = itemView.findViewById(R.id.price_diff_result);
            textViewPriceResult = itemView.findViewById(R.id.price_result);
            textViewNameResult = itemView.findViewById(R.id.high_result);
        }
    }

}
