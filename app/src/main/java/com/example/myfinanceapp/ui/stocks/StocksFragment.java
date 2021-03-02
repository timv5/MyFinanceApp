package com.example.myfinanceapp.ui.stocks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myfinanceapp.R;
import com.example.myfinanceapp.util.BackgroundTask;
import com.example.myfinanceapp.util.VerticalSpacingItemDecoration;

import java.util.List;

import yahoofinance.Stock;

public class StocksFragment extends Fragment {

    private StocksViewModel stocksViewModel;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Stock> stocks;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stocks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.fragment_stocks_recycler_view);
        final StocksRecyclerAdapter stocksRecyclerAdapter = new StocksRecyclerAdapter();
        initRecyclerView(stocksRecyclerAdapter);
        onSwipeRefresh(view, stocksRecyclerAdapter);
    }

    private void initRecyclerView(StocksRecyclerAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        stocksViewModel = new ViewModelProvider(this).get(StocksViewModel.class);
        runInBackground(adapter);
    }

    private void onSwipeRefresh(View view, StocksRecyclerAdapter adapter) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                runInBackground(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void runInBackground(StocksRecyclerAdapter adapter) {
        new BackgroundTask(getActivity()) {
            @Override
            public void doInBackground() {
                stocks = stocksViewModel.getStocks();
            }

            @Override
            public void onPostExecute() {
                adapter.setStocks(stocks);
            }

        }.execute();
    }
}