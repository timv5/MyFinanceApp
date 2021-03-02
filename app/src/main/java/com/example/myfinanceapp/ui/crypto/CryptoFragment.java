package com.example.myfinanceapp.ui.crypto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myfinanceapp.R;
import com.example.myfinanceapp.database.model.CryptoCoin;
import com.example.myfinanceapp.network.dto.Market;
import com.example.myfinanceapp.util.VerticalSpacingItemDecoration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoFragment extends Fragment {

    private CryptoViewModel cryptoViewModel;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Market> markets;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crypto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fragment_crypto_recycler_view);
        final CryptoRecyclerAdapter adapter = new CryptoRecyclerAdapter();
        initRecyclerView(adapter);
        onSwipeRefresh(view, adapter);
        onArchiveListener();
    }

    private void initRecyclerView(CryptoRecyclerAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        cryptoViewModel = new ViewModelProvider(this).get(CryptoViewModel.class);
        getMarkets(adapter);
    }

    private void getMarkets(CryptoRecyclerAdapter adapter) {
        cryptoViewModel.getCryptoMarket().enqueue(new Callback<List<Market>>() {
            @Override
            public void onResponse(Call<List<Market>> call, Response<List<Market>> response) {
                if (response != null && response.body() != null) {
                    markets = response.body();
                    adapter.setMarkets(markets);
                } else {
                    // set dummy data
                    adapter.setMarkets(setDummyList());
                }
            }

            @Override
            public void onFailure(Call<List<Market>> call, Throwable t) {
                // set dummy data
                adapter.setMarkets(setDummyList());
                call.cancel();
            }
        });
    }

    private void onSwipeRefresh(View view, CryptoRecyclerAdapter adapter) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMarkets(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void onArchiveListener() {
        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                        final int position = viewHolder.getAdapterPosition();
                        if (ItemTouchHelper.RIGHT == direction) {
                            cryptoViewModel.insert(setCryptoCoin(markets.get(position)));
                        }
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private CryptoCoin setCryptoCoin(Market market) {
        return CryptoCoin.builder()
                .currentPrice(market.getCurrentPrice().longValue())
                .externalId(market.getId())
                .high24(market.getHigh24().longValue())
                .low24(market.getLow24().longValue())
                .priceChange(market.getPriceChange().longValue())
                .priceChangePercentage24(market.getPriceChangePercentage24().longValue())
                .symbol(market.getSymbol())
                .build();
    }

    private List<Market> setDummyList() {
        Market market = new Market();
        market.setId("1");
        market.setCurrentPrice(BigDecimal.ZERO);
        market.setHigh24(BigDecimal.ZERO);
        market.setLow24(BigDecimal.ZERO);
        market.setPriceChange(BigDecimal.ZERO);
        market.setSymbol("set your api key");
        market.setPriceChangePercentage24(BigDecimal.ZERO);
        List<Market> dummyList = new ArrayList<>();
        dummyList.add(market);
        return dummyList;
    }
}