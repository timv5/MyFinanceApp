package com.example.myfinanceapp.ui.cryptoarchive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myfinanceapp.R;
import com.example.myfinanceapp.database.model.CryptoCoin;
import com.example.myfinanceapp.util.VerticalSpacingItemDecoration;

import java.util.List;

public class CryptoSnapshotArchiveFragment extends Fragment {

    private CryptoSnapshotArchiveViewModel cryptoSnapshotArchiveViewModel;
    private RecyclerView recyclerView;
    private List<CryptoCoin> cryptoCoinList;
    private SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crypto_archive, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fragment_crypto_archive_recycler_view);
        final CryptoSnapshotRecyclerAdapter adapter = new CryptoSnapshotRecyclerAdapter();
        initRecyclerView(adapter);
        onSwipeRefresh(view, adapter);
        onDeleteListener();
    }

    private void initRecyclerView(CryptoSnapshotRecyclerAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        cryptoSnapshotArchiveViewModel = new ViewModelProvider(this).get(CryptoSnapshotArchiveViewModel.class);
        getArchivedCryptoCoins(adapter);
    }

    private void getArchivedCryptoCoins(CryptoSnapshotRecyclerAdapter adapter) {
        cryptoSnapshotArchiveViewModel.getCryptoCoins().observe(getViewLifecycleOwner(), new Observer<List<CryptoCoin>>() {
            @Override
            public void onChanged(List<CryptoCoin> cryptoCoins) {
                adapter.setCoins(cryptoCoins);
                cryptoCoinList = cryptoCoins;
            }
        });
    }

    private void onSwipeRefresh(View view, CryptoSnapshotRecyclerAdapter adapter) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getArchivedCryptoCoins(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void onDeleteListener() {
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
                            cryptoSnapshotArchiveViewModel.delete(cryptoCoinList.get(position));
                        }
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}