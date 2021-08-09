package com.pujiy.mywarehouse.nestedrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pujiy.mywarehouse.R;
import com.pujiy.mywarehouse.data.ChildItem;

import java.util.List;

public class ChildItemAdapter extends RecyclerView
        .Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItem> ChildItemList;

    public ChildItemAdapter(List<ChildItem> childItemList) {
        ChildItemList = childItemList;
    }

    @NonNull
    @Override
    public ChildItemAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.child_item,
                        viewGroup, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildItemAdapter.ChildViewHolder holder, int position) {

        // Create an instance of the ChildItem
        // class for the given position
        ChildItem childItem
                = ChildItemList.get(position);

        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself
        holder
                .ChildItemTitle
                .setText(childItem.getChildItemTitle());
    }

    @Override
    public int getItemCount() {
        return ChildItemList.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView ChildItemTitle;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ChildItemTitle
                    = itemView.findViewById(
                    R.id.child_item_title);
        }
    }
}
