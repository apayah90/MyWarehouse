package com.pujiy.mywarehouse.data;

import java.io.Serializable;
import java.util.ArrayList;

public class InventoryJournalParent implements Serializable {

    private String BatchName;
    private ArrayList<InventoryJournal> childInventoryJournal;

    public InventoryJournalParent(String batchName, ArrayList<InventoryJournal> childInventoryJournal) {
        this.BatchName = batchName;
        this.childInventoryJournal = childInventoryJournal;
    }

    public String getBatchName() {
        return BatchName;
    }

    public void setBatchName(String batchName) {
        this.BatchName = batchName;
    }

    public ArrayList<InventoryJournal> getChildInventoryJournal() {
        return childInventoryJournal;
    }

    public void setChildInventoryJournal(ArrayList<InventoryJournal> childInventoryJournal) {
        this.childInventoryJournal = childInventoryJournal;
    }
}
