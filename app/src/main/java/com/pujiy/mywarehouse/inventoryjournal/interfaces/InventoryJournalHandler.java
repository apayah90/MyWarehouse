package com.pujiy.mywarehouse.inventoryjournal.interfaces;

import com.pujiy.mywarehouse.data.InventoryJournal;

import java.util.List;

public interface InventoryJournalHandler {

    void onStartGetInventoryJournal();

    void onGetInventoryJournal(List<InventoryJournal> inventoryJournalList);

    void onStartInventoryJournal(InventoryJournal inventoryJournal);

    void goToSelectBatch();

    void onError(Throwable throwable);
}
