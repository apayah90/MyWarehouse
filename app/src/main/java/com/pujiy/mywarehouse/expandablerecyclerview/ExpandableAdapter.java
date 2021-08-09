package com.pujiy.mywarehouse.expandablerecyclerview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pujiy.mywarehouse.R;
import com.pujiy.mywarehouse.data.DummyParentData;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.MyViewHolder> {

    private ArrayList<DummyParentData> dummyParentDataList;

    public ExpandableAdapter(ArrayList<DummyParentData> dummyParentDataList) {
        this.dummyParentDataList = dummyParentDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parent_child_listing, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DummyParentData dummyParentData = dummyParentDataList.get(position);
        holder.txtParentName.setText(dummyParentData.getParentName());
        //
        int noOfChildTextVIews = holder.linearLayoutChildItem.getChildCount();
        for (int index = 0; index < noOfChildTextVIews; index++) {
            TextView currentTextView = (TextView) holder.linearLayoutChildItem.getChildAt(index);
            TextView currentTextView2 = (TextView) holder.linearLayoutChildItem.getChildAt(index);
            currentTextView.setVisibility(View.VISIBLE);
            currentTextView2.setVisibility(View.VISIBLE);

        }

        int noOfChild = dummyParentData.getChildDataItems().size();
        if (noOfChild < noOfChildTextVIews) {
            for (int index = noOfChild; index < noOfChildTextVIews; index++) {
                TextView currentTextView = (TextView) holder.linearLayoutChildItem.getChildAt(index);
                TextView currentTextView2 = (TextView) holder.linearLayoutChildItem.getChildAt(index);

                currentTextView.setVisibility(View.GONE);
                currentTextView2.setVisibility(View.GONE);
            }
        }

        for (int textVIewIndex = 0; textVIewIndex < noOfChild; textVIewIndex++) {
            TextView currentTextVIew = (TextView) holder.linearLayoutChildItem.getChildAt(textVIewIndex);
            TextView currentTextVIew2 = (TextView) holder.linearLayoutChildItem.getChildAt(textVIewIndex);
            currentTextVIew.setText(dummyParentData.getChildDataItems().get(textVIewIndex).getChildName());
            currentTextVIew2.setText(dummyParentData.getChildDataItems().get(textVIewIndex).getSecondChildName());

        }

    }

    @Override
    public int getItemCount() {
        return dummyParentDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private TextView txtParentName;
        private LinearLayout linearLayoutChildItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            txtParentName = itemView.findViewById(R.id.tv_parentName);

            linearLayoutChildItem = itemView.findViewById(R.id.ll_child_items);
            linearLayoutChildItem.setVisibility(View.GONE);

            int intMaxNoOfChild = 0;
            for (int index = 0; index < dummyParentDataList.size(); index++) {
                int intMaxSizeTemp = dummyParentDataList.get(index).getChildDataItems().size();
                if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
            }

            for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {
                TextView textView = new TextView(context);
                textView.setId(indexView);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setOnClickListener(this);
                linearLayoutChildItem.addView(textView, layoutParams);

                TextView textView2 = new TextView(context);
                textView2.setId(indexView+30);
//                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView2.setOnClickListener(this);
                linearLayoutChildItem.addView(textView2, layoutParams);
            }
            txtParentName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tv_parentName) {
                if (linearLayoutChildItem.getVisibility() == View.VISIBLE) {
                    linearLayoutChildItem.setVisibility(View.GONE);
                } else  {
                    linearLayoutChildItem.setVisibility(View.VISIBLE);
                }
            } else {
                TextView textViewClicked = (TextView) v;
                Toast.makeText(context, "" + textViewClicked.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
