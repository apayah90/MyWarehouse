package com.pujiy.mywarehouse.inventoryjournal.dialog;

import com.pujiy.mywarehouse.data.InventoryJournalBatch;
import com.pujiy.mywarehouse.databinding.DialogKartubarangSelectBatchBinding;

import java.util.ArrayList;
import java.util.List;

public class DialogInventoryJournalBatchViewModel {

    List<String> inventoryJournalBatches = new ArrayList<>();
    private DialogInventoryJournalBatchAdapter adapter;



    public DialogInventoryJournalBatchAdapter getAdapter() {
        return adapter;
    }

    public void showStockBatch(List<String> inventoryJournalBatches) {
        this.inventoryJournalBatches.clear();
        this.inventoryJournalBatches.addAll(inventoryJournalBatches);
        adapter = new DialogInventoryJournalBatchAdapter(inventoryJournalBatches);
        adapter.notifyDataSetChanged();

    }
}
