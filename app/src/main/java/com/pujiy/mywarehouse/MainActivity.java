package com.pujiy.mywarehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pujiy.mywarehouse.base.BaseActivity;
import com.pujiy.mywarehouse.data.InventoryJournal;
import com.pujiy.mywarehouse.databinding.ActivityMainBinding;
import com.pujiy.mywarehouse.inventoryjournal.InventoryJournalViewModel;
import com.pujiy.mywarehouse.inventoryjournal.adapter.InventoryJournalAdapter;
import com.pujiy.mywarehouse.inventoryjournal.interfaces.InventoryJournalHandler;
import com.pujiy.mywarehouse.inventoryjournal.selectbatch.SelectBatchActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, InventoryJournalViewModel>
        implements InventoryJournalHandler {

    private static final String TAG = MainActivity.class.getSimpleName();

    private InventoryJournalViewModel viewModel;
    private ActivityMainBinding binding;
    private List<InventoryJournal> listInventoryJournal = new ArrayList<>();
    private InventoryJournalAdapter adapter;
//    private MaterialDatePicker.Builder<Pair<Pair, Pair>>  materialDatePicker;
//    private Calendar currentDate = Calendar.getInstance();
    private int pos;

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public InventoryJournalViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        viewModel = new InventoryJournalViewModel(this, this);
        super.onCreate(savedInstanceState);

        binding = getViewDataBinding();
        binding.setVm(viewModel);

        setUp();

    }

    private void setUp() {

        binding.rvInventoryJournal.setHasFixedSize(true);
        binding.rvInventoryJournal.setLayoutManager(new LinearLayoutManager(this));
        binding.rvInventoryJournal.setItemAnimator(new DefaultItemAnimator());
        binding.rvInventoryJournal.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new InventoryJournalAdapter(listInventoryJournal);
        binding.rvInventoryJournal.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Log.d(TAG, "setUp: "+listInventoryJournal);
    }

    private void sortByLuarKota() {


    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refresh("PUSAT", "Headset");
    }

    @Override
    public void onStartGetInventoryJournal() {
        listInventoryJournal.clear();
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onGetInventoryJournal(List<InventoryJournal> inventoryJournalList) {
        this.listInventoryJournal.clear();
        this.listInventoryJournal.addAll(inventoryJournalList);
        adapter.notifyDataSetChanged();
        Log.d(TAG, "onGetInventoryJournal: "+inventoryJournalList.toString());

    }

    @Override
    public void onStartInventoryJournal(InventoryJournal inventoryJournal) {

    }

    @Override
    public void goToSelectBatch() {
        SelectBatchActivity.startThisActivity(this);
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this, "Kesalahan terjadi "+throwable, Toast.LENGTH_SHORT).show();

    }
}