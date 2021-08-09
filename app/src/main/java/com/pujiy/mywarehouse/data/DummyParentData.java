package com.pujiy.mywarehouse.data;

import java.io.Serializable;
import java.util.ArrayList;

public class DummyParentData implements Serializable {

    private String parentName;
    private ArrayList<DummyChildData> childDataItems;

    public DummyParentData(String parentName, ArrayList<DummyChildData> childDataItems) {
        this.parentName = parentName;
        this.childDataItems = childDataItems;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public ArrayList<DummyChildData> getChildDataItems() {
        return childDataItems;
    }

    public void setChildDataItems(ArrayList<DummyChildData> childDataItems) {
        this.childDataItems = childDataItems;
    }
}
