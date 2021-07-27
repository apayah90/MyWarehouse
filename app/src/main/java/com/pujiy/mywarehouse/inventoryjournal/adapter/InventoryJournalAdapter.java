package com.pujiy.mywarehouse.inventoryjournal.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pujiy.mywarehouse.R;
import com.pujiy.mywarehouse.base.BaseViewHolder;
import com.pujiy.mywarehouse.data.InventoryJournal;
import com.pujiy.mywarehouse.databinding.ListInventoryJournalBinding;

import java.util.List;

public class InventoryJournalAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<InventoryJournal> listInventoryJournal;

    public InventoryJournalAdapter(List<InventoryJournal> listJournal) {
        this.listInventoryJournal = listJournal;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListInventoryJournalBinding binding = ListInventoryJournalBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_inventory_journal, parent, false));
        return new InventoryJournalViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return listInventoryJournal.size();
    }

    public class InventoryJournalViewHolder extends BaseViewHolder<ListInventoryJournalBinding> {

        private ListInventoryJournalBinding binding;

        public InventoryJournalViewHolder(ListInventoryJournalBinding binding) {
            super(binding);
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBind(int position) {
            this.binding.setVh(this);
            this.binding.setInventoryjournal(listInventoryJournal.get(position));
            this.binding.executePendingBindings();
            this.binding.txtBalance.setText("("+listInventoryJournal.get(position).getBALANCE()+")");
            int result = Integer.parseInt(listInventoryJournal.get(position).getRESULT());
            if (result < 0) {
                this.binding.txtQty.setTextColor(Color.parseColor("#ee4a4a"));
            }
            else if (result == 0 ) {
                this.binding.txtQty.setTextColor(Color.parseColor("#000000"));
            }

            else {
                this.binding.txtQty.setText("+"+listInventoryJournal.get(position).getRESULT());
                this.binding.txtQty.setTextColor(Color.parseColor("#01ab6c"));
            }

        }
    }
}
