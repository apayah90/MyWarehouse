package com.pujiy.mywarehouse.testbaseadapter;

import android.content.Context;
import android.widget.Toast;

import com.pujiy.mywarehouse.base.BaseViewModel;
import com.pujiy.mywarehouse.inventoryjournal.interfaces.RecyclerClickListener;

import java.util.ArrayList;
import java.util.List;


public class DialogBatchViewModel extends BaseViewModel implements RecyclerClickListener {

    private final List<String> listBatches = new ArrayList<>();
    private final Listener listener;

    private final DialogBatchAdapter adapter;

    public DialogBatchViewModel(Context context, Listener listener) {
        super(context);
        this.listener = listener;

        adapter = new DialogBatchAdapter(listBatches);
        adapter.setOnRecyclerClickListener(this);
    }

    public DialogBatchAdapter getAdapter() {
        return adapter;
    }

    public void showStockBatch(List<String> batchsList) {

        this.listBatches.clear();
        this.listBatches.addAll(batchsList);
        adapter.updateDataSet();
    }

    @Override
    public void onClick(int position) {
        String batch = listBatches.get(position);
        Toast.makeText(getContext(), batch, Toast.LENGTH_SHORT).show();

        listener.onBatchSelected(batch);
    }

    public interface Listener {
        void onBatchSelected(String batch);
    }
}
