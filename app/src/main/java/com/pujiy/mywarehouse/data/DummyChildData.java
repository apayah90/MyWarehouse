package com.pujiy.mywarehouse.data;

import java.io.Serializable;

public class DummyChildData implements Serializable {
    private String childName;
    private String secondChildName;

    public String getSecondChildName() {
        return secondChildName;
    }

    public void SecondChildName(String secondChildName) {
        this.secondChildName = secondChildName;
    }

    public DummyChildData(String childName, String secondChildName) {
        this.childName = childName;
        this.secondChildName = secondChildName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }
}
