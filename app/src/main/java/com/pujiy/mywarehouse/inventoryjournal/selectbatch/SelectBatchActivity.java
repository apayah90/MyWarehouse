package com.pujiy.mywarehouse.inventoryjournal.selectbatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.pujiy.mywarehouse.R;
import com.pujiy.mywarehouse.base.BaseActivity;
import com.pujiy.mywarehouse.databinding.ActivitySelectBatchBinding;
import com.pujiy.mywarehouse.inventoryjournal.selectbatch.adapter.SelectBatchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SelectBatchActivity extends BaseActivity<ActivitySelectBatchBinding, SelectBatchViewModel> {

    private ActivitySelectBatchBinding binding;
    private List<String> list = new ArrayList<>();
    private SelectBatchAdapter adapter;
    private SelectBatchViewModel viewModel;


    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_batch;
    }

    @Override
    public SelectBatchViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        viewModel = new SelectBatchViewModel(this);
        super.onCreate(savedInstanceState);

        list.add("2019");
        list.add("2019");
        list.add("2021");
        list.add("2020");

        binding = getViewDataBinding();
        binding.setVm(viewModel);

        binding.rvSelectBatch.setHasFixedSize(true);
        binding.rvSelectBatch.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSelectBatch.setItemAnimator(new DefaultItemAnimator());
        binding.rvSelectBatch.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new SelectBatchAdapter(list);
        binding.rvSelectBatch.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public static void startThisActivity(Context context) {

        Intent intent = new Intent(context, SelectBatchActivity.class);
        context.startActivity(intent);

    }

}