package com.pujiy.mywarehouse.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<E extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected final E binding;

    public BaseViewHolder(E binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public abstract void onBind(int position);
}
