package com.pujiy.mywarehouse.expandablerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.pujiy.mywarehouse.R;
import com.pujiy.mywarehouse.data.DummyChildData;
import com.pujiy.mywarehouse.data.DummyParentData;

import java.util.ArrayList;

public class ExpandableRecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_recycler);

        context = ExpandableRecyclerActivity.this;
        recyclerView = findViewById(R.id.recyclerView);
        ExpandableAdapter adapter = new ExpandableAdapter(getDummyDataToPass());
        Log.d("TAG", "onCreate: "+getDummyDataToPass().size());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        Log.d("TAG", "onCreate: "+adapter.getItemCount());
    }

    private ArrayList<DummyParentData> getDummyDataToPass() {

        ArrayList<DummyParentData> parentData = new ArrayList<>();
        ArrayList<DummyChildData> childData;

        //Fill Parent and Child List
        childData = new ArrayList<>();
        childData.add(new DummyChildData("A Child 1", "Sub"));
        childData.add(new DummyChildData("A Child 2", "Sub"));
        childData.add(new DummyChildData("A Child 3", "Sub"));
        parentData.add(new DummyParentData("A Parent, 3 Children", childData));
        /////////
        childData = new ArrayList<>();
        childData.add(new DummyChildData("B Child 1", "Sub"));
        childData.add(new DummyChildData("B Child 2", "Sub"));
        childData.add(new DummyChildData("B Child 3", "Sub"));
        childData.add(new DummyChildData("B Child 4", "Sub"));
        childData.add(new DummyChildData("B Child 5", "Sub"));
        childData.add(new DummyChildData("B Child 6", "Sub"));
        parentData.add(new DummyParentData("A Parent, 6 Children", childData));
        /////////
        childData = new ArrayList<>();
        childData.add(new DummyChildData("B Child 1", "Sub"));
        childData.add(new DummyChildData("B Child 2", "Sub"));
        childData.add(new DummyChildData("B Child 3", "Sub"));
        childData.add(new DummyChildData("B Child 4", "Sub"));
        childData.add(new DummyChildData("B Child 5", "Sub"));
        childData.add(new DummyChildData("B Child 6", "Sub"));
        parentData.add(new DummyParentData("A Parent, 6 Children", childData));
        /////////
        childData = new ArrayList<>();
        childData.add(new DummyChildData("C Child 1", "Sub"));
        childData.add(new DummyChildData("C Child 2", "Sub"));
        childData.add(new DummyChildData("C Child 3", "Sub"));
        childData.add(new DummyChildData("C Child 4", "Sub"));
        childData.add(new DummyChildData("C Child 5", "Sub"));
        childData.add(new DummyChildData("C Child 6", "Sub"));
        childData.add(new DummyChildData("C Child 7", "Sub"));
        childData.add(new DummyChildData("C Child 8", "Sub"));
        childData.add(new DummyChildData("C Child 9", "Sub"));
        parentData.add(new DummyParentData("C Parent, 9 Children", childData));
        /////////
        return parentData;


    }
}