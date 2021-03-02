package com.example.myfinanceapp.ui.crypto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinanceapp.R;
import com.example.myfinanceapp.network.dto.Market;

import java.util.ArrayList;
import java.util.List;

public class CryptoRecyclerAdapter extends RecyclerView.Adapter<CryptoRecyclerAdapter.CryptoMarketHolder>{

    private List<Market> markets = new ArrayList<>();

    @NonNull
    @Override
    public CryptoMarketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.crypto_item, parent, false);
        return new CryptoMarketHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoMarketHolder holder, int position) {
        Market market = this.markets.get(position);
        holder.textViewHigh24Result.setText(String.valueOf(market.getHigh24()));
        holder.textViewLow24Result.setText(String.valueOf(market.getLow24()));
        holder.textViewNamePriceDiff.setText(String.valueOf(market.getPriceChange()));
        holder.textViewPriceResult.setText(String.valueOf(market.getCurrentPrice()));
        holder.textViewSymbolResult.setText(market.getSymbol());
    }

    @Override
    public int getItemCount() {
        return this.markets.size();
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
        notifyDataSetChanged();
    }

    static class CryptoMarketHolder extends RecyclerView.ViewHolder {

        private final TextView textViewSymbolResult;
        private final TextView textViewHigh24Result;
        private final TextView textViewLow24Result;
        private final TextView textViewPriceResult;
        private final TextView textViewNamePriceDiff;

        public CryptoMarketHolder(@NonNull View itemView) {
            super(itemView);

            textViewHigh24Result = itemView.findViewById(R.id.high_result);
            textViewSymbolResult = itemView.findViewById(R.id.symbol_result);
            textViewLow24Result = itemView.findViewById(R.id.low_result);
            textViewPriceResult = itemView.findViewById(R.id.price_result);
            textViewNamePriceDiff = itemView.findViewById(R.id.price_diff_result);
        }
    }

}
