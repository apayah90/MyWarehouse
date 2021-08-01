package com.pujiy.mywarehouse.inventoryjournal.selectbatch.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pujiy.mywarehouse.R;
import com.pujiy.mywarehouse.base.BaseViewHolder;
import com.pujiy.mywarehouse.databinding.ListInventoryJournalBatchBinding;

import java.util.List;

public class SelectBatchAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<String> listBatch;

    public SelectBatchAdapter(List<String> listBatch) {
        this.listBatch = listBatch;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListInventoryJournalBatchBinding binding = ListInventoryJournalBatchBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_inventory_journal_batch, parent, false));
        return new SelectBatchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return listBatch.size();
    }

    public class SelectBatchViewHolder extends BaseViewHolder<ListInventoryJournalBatchBinding> {

        private ListInventoryJournalBatchBinding binding;


        public SelectBatchViewHolder(ListInventoryJournalBatchBinding binding) {
            super(binding);
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
        }
    }
}
