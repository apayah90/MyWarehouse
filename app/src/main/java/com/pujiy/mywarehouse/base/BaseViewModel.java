package com.pujiy.mywarehouse.base;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected ProgressDialog progressDialog;
    private CompositeDisposable mCompositeDisposable;
    private WeakReference<Context> mContext;
//    private SessionHandler sessionHandler;
//    private DBHelper dbHelper;

    public BaseViewModel(Context context) {
        this.mContext = new WeakReference<>(context);
//        this.sessionHandler = new SessionHandler(context);
//        this.dbHelper = DBHelper.getInsstance(context);
        this.progressDialog = new ProgressDialog(context);
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    protected Context getContext() {
        return this.mContext.get();
    }
}
