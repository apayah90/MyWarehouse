package com.pujiy.mywarehouse.data;

public class ChildItem {

    // Declaration of the variable
    private String ChildItemTitle;
    //
    private String ChildItemBatch;

    public ChildItem(String childItemTitle, String childItemBatch) {
        ChildItemTitle = childItemTitle;
        ChildItemBatch = childItemBatch;
    }

    public String getChildItemBatch() {
        return ChildItemBatch;
    }

    public void setChildItemBatch(String childItemBatch) {
        ChildItemBatch = childItemBatch;
    }

    public String getChildItemTitle() {
        return ChildItemTitle;
    }

    public void setChildItemTitle(String childItemTitle) {
        ChildItemTitle = childItemTitle;
    }
}
