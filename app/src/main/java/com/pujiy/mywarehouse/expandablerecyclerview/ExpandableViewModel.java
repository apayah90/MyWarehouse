package com.pujiy.mywarehouse.expandablerecyclerview;

import android.content.Context;
import android.view.View;

import com.pujiy.mywarehouse.data.DummyChildData;
import com.pujiy.mywarehouse.data.InventoryJournal;
import com.pujiy.mywarehouse.inventoryjournal.InventoryJournalViewModel;
import com.pujiy.mywarehouse.inventoryjournal.interfaces.InventoryJournalHandler;

import java.util.ArrayList;

public class ExpandableViewModel {

    private final InventoryJournalViewModel viewModel;
    private InventoryJournalHandler inventoryJournalHandler;

    public ExpandableViewModel(Context context, InventoryJournalHandler inventoryJournalHandler) {
        viewModel = new InventoryJournalViewModel(context, inventoryJournalHandler);
    }

    public void onRefresh() {

        viewModel.refresh();


    }
}
