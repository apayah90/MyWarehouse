package com.pujiy.mywarehouse.data;

import java.util.List;

public class ParentItem {

    // Declaration of the variables
    private String ParentItemTitle;
    private List<ChildItem> ChildItemList;

    public ParentItem(String parentItemTitle, List<ChildItem> childItemList) {
        ParentItemTitle = parentItemTitle;
        ChildItemList = childItemList;
    }

    public String getParentItemTitle() {
        return ParentItemTitle;
    }

    public void setParentItemTitle(String parentItemTitle) {
        ParentItemTitle = parentItemTitle;
    }

    public List<ChildItem> getChildItemList() {
        return ChildItemList;
    }

    public void setChildItemList(List<ChildItem> childItemList) {
        ChildItemList = childItemList;
    }
}
