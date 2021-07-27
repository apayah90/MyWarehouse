package com.pujiy.mywarehouse.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pujiy.mywarehouse.inventoryjournal.interfaces.RecyclerClickListener;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseRecyclerAdapter<E> extends RecyclerView.Adapter<BaseViewHolder<?>> {

    private static final String TAG = BaseRecyclerAdapter.class.getSimpleName();
    protected final List<E> showList = new ArrayList<>();
    private final List<E> actualList;
    protected RecyclerClickListener listener;
    private Disposable disposable;

    private String query;

    public BaseRecyclerAdapter(List<E> originalList) {
        this.actualList = originalList;
        this.showList.addAll(actualList);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<?> holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    @Override
    public long getItemId(int position) {
        return showList.get(position).hashCode();
    }

    public void filter(String query) {

        if (query == null) {
            updateDataSet();
            return;
        }else {
            query = query.trim();

            if (query.isEmpty()) {
                updateDataSet();
                return;
            }
        }
        this.query = query;
        cancelSearch();
        showList.clear();
        disposable = search(this.query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> {
                    showList.add(e);
                }, Throwable::printStackTrace, this::notifyDataSetChanged);
    }

    protected abstract boolean filter(String query, E e);

    private Observable<E> search(String query) {
        return Observable.<E>create(e -> {
            for (int i = 0; i < actualList.size(); i++) {
                e.onNext(actualList.get(i));
            }
            e.onComplete();
        }).filter(e -> filter(query, e));
    }

    private void cancelSearch() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }

    public void setOnRecyclerClickListener(RecyclerClickListener listener) {
        this.listener = listener;
    }

    public void updateDataSet() {
        cancelSearch();

        this.showList.clear();
        this.showList.addAll(actualList);
        notifyDataSetChanged();

        if (query != null)
            filter(query);
    }
}
