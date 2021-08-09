package com.pujiy.mywarehouse.inventoryjournal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pujiy.mywarehouse.R;
import com.pujiy.mywarehouse.data.InventoryJournalParent;

import java.util.ArrayList;

public class InventoryJournalExpandableAdapter extends RecyclerView.Adapter<InventoryJournalExpandableAdapter.MyViewHolder> {

    private ArrayList<InventoryJournalParent> parentDataList;

    private ArrayList<InventoryJournalParent> parentaDataList;

    public InventoryJournalExpandableAdapter(ArrayList<InventoryJournalParent> parentDataList) {
        this.parentDataList = parentDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private TextView txtParentName, txtStokAwal, txtTotalStokIn, txtTotalStokOut;
        private LinearLayout linearLayoutChildItem;
        private TableLayout tblStokAwal, tblTotalStok;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            txtParentName = itemView.findViewById(R.id.tv_parentName);

            linearLayoutChildItem = itemView.findViewById(R.id.ll_child_items);
            linearLayoutChildItem.setVisibility(View.GONE);

            tblStokAwal = itemView.findViewById(R.id.tbl_stok_awal);
            tblStokAwal.setVisibility(View.GONE);

            txtStokAwal = itemView.findViewById(R.id.txt_total_stock_awal_in);

            tblTotalStok = itemView.findViewById(R.id.tbl_total_stock);
            tblTotalStok.setVisibility(View.GONE);

            txtTotalStokIn = itemView.findViewById(R.id.txt_total_stock_in);
            txtTotalStokOut = itemView.findViewById(R.id.txt_total_stock_out);

            int intMaxNoOfChild = 0;
            for(int index = 0; index < parentDataList.size(); index++) {
                int intMaxSizeTemp = parentDataList.get(index).getChildInventoryJournal().size();
                if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
            }

            for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {
                TextView textView = new TextView(context);
                textView.setId(indexView);
                textView.setPadding(0,8,0,8);

            }
        }

        @Override
        public void onClick(View v) {

        }
    }
}
