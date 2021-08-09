package com.pujiy.mywarehouse.nestedrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pujiy.mywarehouse.MainActivity;
import com.pujiy.mywarehouse.R;
import com.pujiy.mywarehouse.data.ChildItem;
import com.pujiy.mywarehouse.data.ParentItem;

import java.util.ArrayList;
import java.util.List;

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
                ParentItemList());

        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem
                .setAdapter(parentItemAdapter);
        ParentRecyclerViewItem
                .setLayoutManager(layoutManager);
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
                ChildItemList());
        itemList.add(item1);
        ParentItem item2
                = new ParentItem(
                "Title 3",
                ChildItemList());
        itemList.add(item2);
        ParentItem item3
                = new ParentItem(
                "Title 4",
                ChildItemList());
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

        ChildItemList.add(new ChildItem("Card 1"));
        ChildItemList.add(new ChildItem("Card 2"));
        ChildItemList.add(new ChildItem("Card 3"));
        ChildItemList.add(new ChildItem("Card 4"));

        return ChildItemList;
    }
}