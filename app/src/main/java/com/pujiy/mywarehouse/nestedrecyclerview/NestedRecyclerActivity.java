package com.pujiy.mywarehouse.nestedrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.pujiy.mywarehouse.MainActivity;
import com.pujiy.mywarehouse.R;
import com.pujiy.mywarehouse.data.ChildItem;
import com.pujiy.mywarehouse.data.ParentItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NestedRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_recycler);

        RecyclerView
                ParentRecyclerViewItem
                = findViewById(
                R.id.parent_recyclerview);

        // Initialise the Linear layout manager
        LinearLayoutManager
                layoutManager
                = new LinearLayoutManager(
                NestedRecyclerActivity.this);

        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        ParentItemAdapter
                parentItemAdapter
                = new ParentItemAdapter(
                groupChildItemList());

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem
                .setAdapter(parentItemAdapter);
        ParentRecyclerViewItem
                .setLayoutManager(layoutManager);

        groupChildItemList();
    }

    private List<ParentItem> ParentItemList()
    {
        List<ParentItem> itemList
                = new ArrayList<>();

        ParentItem item
                = new ParentItem(
                "Title 1",
                ChildItemList());
        itemList.add(item);
        ParentItem item1
                = new ParentItem(
                "Title 2",
                ChildItemList2());
        itemList.add(item1);
        ParentItem item2
                = new ParentItem(
                "Title 3",
                ChildItemList2());
        itemList.add(item2);
        ParentItem item3
                = new ParentItem(
                "Title 4",
                ChildItemList2());
        itemList.add(item3);

        return itemList;
    }

    // Method to pass the arguments
    // for the elements
    // of child RecyclerView
    private List<ChildItem> ChildItemList()
    {
        List<ChildItem> ChildItemList
                = new ArrayList<>();

        ChildItemList.add(new ChildItem("Card 1", "A"));
        ChildItemList.add(new ChildItem("Card 2", "A1"));
        ChildItemList.add(new ChildItem("Card 3", "A1"));
        ChildItemList.add(new ChildItem("Card 4", "A1"));

        return ChildItemList;
    }

    private List<ChildItem> ChildItemList2()
    {
        List<ChildItem> ChildItemList2
                = new ArrayList<>();

        ChildItemList2.add(new ChildItem("Card B1", "B1"));
        ChildItemList2.add(new ChildItem("Card B1", "B1"));
        ChildItemList2.add(new ChildItem("Card B1", "B1"));
        ChildItemList2.add(new ChildItem("Card B1", "B1"));
        ChildItemList2.add(new ChildItem("Card B1", "B1"));

        ChildItemList2.add(new ChildItem("Card B2", "B2"));
        ChildItemList2.add(new ChildItem("Card B2", "B2"));
        ChildItemList2.add(new ChildItem("Card B2", "B2"));
        ChildItemList2.add(new ChildItem("Card B2", "B2"));
        ChildItemList2.add(new ChildItem("Card B2", "B2"));

        ChildItemList2.add(new ChildItem("Card C2", "C2"));
        ChildItemList2.add(new ChildItem("Card C2", "C2"));
        ChildItemList2.add(new ChildItem("Card C2", "C2"));
        ChildItemList2.add(new ChildItem("Card C2", "C2"));
        ChildItemList2.add(new ChildItem("Card C2", "C2"));


        return ChildItemList2;
    }

//    Map<Integer, List<YourObject>> yearLists = new HashMap<Integer, List<YOurObject>>();
//    List<YourObject> originalList = ...; //initialized or received from somewhere
//
//for (YourObject object : originalList) {
//        int year = object.getDate().getYear(); //or something
//        List<YourObject> yearList = yearLists.get(year);
//
//        if (yearList == null) {
//            yearList = new ArrayList<YourObject>();
//        }
//
//        yearList.add(object);
//        yearLists.put(year, yearList);
//    }

    public List<ParentItem> groupChildItemList() {

        Map<String, List<ChildItem>> childItemByGroupList = new HashMap<String, List<ChildItem>>();
        List<ChildItem> originalList = ChildItemList2();
        List<ChildItem> listValue = new ArrayList<>();

        List<ParentItem> itemList
                = new ArrayList<>();

        for (ChildItem object : originalList) {
            String batch = object.getChildItemBatch();
            List<ChildItem> childItemList = childItemByGroupList.get(batch);

            if (childItemList == null) {
                childItemList = new ArrayList<ChildItem>();
            }

            childItemList.add(object);
            childItemByGroupList.put(batch, childItemList); // -> returnnya "B1", object


        }

        Set<String> keySet = childItemByGroupList.keySet();
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);

        Collection<List<ChildItem>> values = childItemByGroupList.values();
        ArrayList<List<ChildItem>> listOfValue = new ArrayList<>(values);



        for (String itemKey : listOfKeys) {
            Log.d("TAG", "itemKey: "+itemKey);
        }

        Log.d("TAG", "itemValue: "+listOfValue.size());
        Log.d("TAG", "itemOfValue0: "+ listOfValue.get(0).size());
        Log.d("TAG", "itemOfValue1: "+ listOfValue.get(1).size());
        Log.d("TAG", "itemOfValue2: "+ listOfValue.get(2).size());

        //
        List<ChildItem> ChildItemGroupList
                = new ArrayList<>();
        for (int i = 0; i < listOfValue.size(); i++) {

            Log.d("TAG", "itemOfValue: "+ listOfValue.get(i).size());
            Log.d("TAG", "count: "+i);

            ChildItemGroupList.addAll(listOfValue.get(i));



            ParentItem item
                    = new ParentItem(
                    listOfKeys.get(i),
                    listOfValue.get(i));
            itemList.add(item);

        }

//        List<ChildItem> ChildItemGroupList
//                = new ArrayList<>(listOfValue.get(0));

        for (ChildItem item : ChildItemGroupList) {
            Log.d("TAG", "groupChildItemList: "+item.getChildItemTitle());
        }

//        for (List<ChildItem> itemValue : listOfValue) {
//
//            listValue.addAll(itemValue);
//
////            Log.d("TAG", "itemValue: "+ itemValue);
//        }

//        for (List<ChildItem> listValueItem : listOfValue) {
//            Log.d("TAG", "itemBatch: "+ listValueItem.getChildItemBatch() +"itemValue: "+listValueItem.getChildItemTitle());
//        }

        return itemList;
    }
}