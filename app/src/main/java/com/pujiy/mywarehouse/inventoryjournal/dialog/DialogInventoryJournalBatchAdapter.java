package com.pujiy.mywarehouse.inventoryjournal.dialog;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pujiy.mywarehouse.R;
import com.pujiy.mywarehouse.base.BaseRecyclerAdapter;
import com.pujiy.mywarehouse.base.BaseViewHolder;
import com.pujiy.mywarehouse.data.InventoryJournalBatch;
import com.pujiy.mywarehouse.databinding.DialogListBatchBinding;
import com.pujiy.mywarehouse.databinding.ListInventoryJournalBatchBinding;

import java.util.List;

public class DialogInventoryJournalBatchAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<String> listInventoryJournalBatch;

    public DialogInventoryJournalBatchAdapter(List<String> listInventoryJournalBatch) {
        this.listInventoryJournalBatch = listInventoryJournalBatch;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DialogListBatchBinding binding =
 DialogListBatchBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_list_batch, parent, false));
        return new InventoryJournalBatchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return listInventoryJournalBatch.size();
    }

    public class InventoryJournalBatchViewHolder extends BaseViewHolder<DialogListBatchBinding> {

        private DialogListBatchBinding binding;

        public InventoryJournalBatchViewHolder(DialogListBatchBinding binding) {
            super(binding);
            this.binding = binding;
        }


        @Override
        public void onBind(int position) {
//            this.binding.setVh(this);
            this.binding.txtBatch.setText(listInventoryJournalBatch.get(position));

        }
    }
}
