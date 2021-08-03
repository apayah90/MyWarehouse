package com.pujiy.mywarehouse.testbaseadapter;

import android.content.Context;

import com.pujiy.mywarehouse.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;


public class DialogBatchViewModel extends BaseViewModel {

    private final List<String> listBatches = new ArrayList<>();

    private final DialogBatchAdapter adapter;

    public DialogBatchViewModel(Context context) {
        super(context);

        adapter = new DialogBatchAdapter(listBatches);

    }

    public DialogBatchAdapter getAdapter() {
        return adapter;
    }

    public void showStockBatch() {

        List<String> stockBatches = new ArrayList<>();
        stockBatches.add("2021");
        this.listBatches.clear();
        this.listBatches.addAll(stockBatches);
        adapter.updateDataSet();
    }
}
