package com.example.myfinanceapp.ui.cryptoarchive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfinanceapp.R;
import com.example.myfinanceapp.database.model.CryptoCoin;

import java.util.ArrayList;
import java.util.List;

public class CryptoSnapshotRecyclerAdapter extends RecyclerView.Adapter<CryptoSnapshotRecyclerAdapter.CryptoSnapshotMarketHolder> {

    private List<CryptoCoin> coins = new ArrayList<>();

    @NonNull
    @Override
    public CryptoSnapshotMarketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.crypto_item, parent, false);
        return new CryptoSnapshotMarketHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoSnapshotMarketHolder holder, int position) {
        CryptoCoin cryptoCoin = this.coins.get(position);
        holder.textViewHigh24Result.setText(String.valueOf(cryptoCoin.getHigh24()));
        holder.textViewLow24Result.setText(String.valueOf(cryptoCoin.getLow24()));
        holder.textViewNamePriceDiff.setText(String.valueOf(cryptoCoin.getPriceChange()));
        holder.textViewPriceResult.setText(String.valueOf(cryptoCoin.getCurrentPrice()));
        holder.textViewSymbolResult.setText(cryptoCoin.getSymbol());
    }

    @Override
    public int getItemCount() {
        return this.coins.size();
    }

    public void setCoins(List<CryptoCoin> coins) {
        this.coins = coins;
        notifyDataSetChanged();
    }

    static class CryptoSnapshotMarketHolder extends RecyclerView.ViewHolder {

        private final TextView textViewSymbolResult;
        private final TextView textViewHigh24Result;
        private final TextView textViewLow24Result;
        private final TextView textViewPriceResult;
        private final TextView textViewNamePriceDiff;

        public CryptoSnapshotMarketHolder(@NonNull View itemView) {
            super(itemView);

            textViewHigh24Result = itemView.findViewById(R.id.high_result);
            textViewSymbolResult = itemView.findViewById(R.id.symbol_result);
            textViewLow24Result = itemView.findViewById(R.id.low_result);
            textViewPriceResult = itemView.findViewById(R.id.price_result);
            textViewNamePriceDiff = itemView.findViewById(R.id.price_diff_result);
        }
    }

}
