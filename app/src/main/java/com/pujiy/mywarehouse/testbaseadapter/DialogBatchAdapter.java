package com.pujiy.mywarehouse.testbaseadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.pujiy.mywarehouse.base.BaseRecyclerAdapter;
import com.pujiy.mywarehouse.base.BaseViewHolder;
import com.pujiy.mywarehouse.databinding.DialogListBatchBinding;

import java.util.List;

public class DialogBatchAdapter extends BaseRecyclerAdapter<String> {
    private List<String> listKartuBarang;
    public DialogBatchAdapter(List<String> originalList) {
        super(originalList);
    }

    @Override
    protected boolean filter(String query, String s) {
        return false;
    }

    @NonNull
    @Override
    public BaseViewHolder<?> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        DialogListBatchBinding binding =
                DialogListBatchBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new AdjustmentViewHolder(binding);
    }

    public class AdjustmentViewHolder extends BaseViewHolder<DialogListBatchBinding> {

        public AdjustmentViewHolder(DialogListBatchBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {

            binding.setVh(this);
            binding.executePendingBindings();

            binding.txtBatch.setText(showList.get(position));

            binding.getRoot().setOnClickListener(v -> {
                listener.onClick(position);
            });

        }
    }


}
