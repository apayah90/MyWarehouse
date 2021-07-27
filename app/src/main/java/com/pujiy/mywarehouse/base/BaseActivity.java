package com.pujiy.mywarehouse.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.androidnetworking.utils.Utils;

public abstract class BaseActivity<T extends ViewDataBinding, V extends  BaseViewModel> extends
        AppCompatActivity {

    private T mViewDataBinding;
    private V mViewModel;

    public abstract int getBindingVariable();

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UncaughtException exception = new UncaughtException(this);
        Thread.setDefaultUncaughtExceptionHandler(exception);
        performDataBinding();
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

/*
    public void hideKeyboard() {
        Utils.hideKeyboard(getCurrentFocus());
    }

    public void hideKeyboard(View view) {
        Utils.hideKeyboard(view);
    }

    public boolean isNetworkConnected() {
        return Utils.isNetworkConnected(getApplicationContext());
    }

    */

    private void performDataBinding() {
        if (getLayoutId() == -1 || getViewModel() == null || getBindingVariable() == -1)
            return;
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel(): mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mViewModel != null)
            this.mViewModel.onCleared();
    }
}
