package com.pujiy.mywarehouse.inventoryjournal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.androidnetworking.error.ANError;
import com.pujiy.mywarehouse.CustomDialog;
import com.pujiy.mywarehouse.base.BaseActivity;
import com.pujiy.mywarehouse.base.BaseViewModel;
import com.pujiy.mywarehouse.data.InventoryJournal;
import com.pujiy.mywarehouse.data.InventoryJournalBatch;
import com.pujiy.mywarehouse.databinding.DialogKartubarangSelectBatchBinding;
import com.pujiy.mywarehouse.inventoryjournal.dialog.DialogInventoryJournalBatchViewModel;
import com.pujiy.mywarehouse.inventoryjournal.interfaces.InventoryJournalHandler;
import com.pujiy.mywarehouse.inventoryjournal.selectbatch.SelectBatchActivity;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InventoryJournalViewModel extends BaseViewModel {

    private final String TAG = InventoryJournalViewModel.class.getSimpleName();

    private ObservableField<InventoryJournal> inventoryJournal = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> customer = new ObservableField<>();
    public ObservableField<String> document = new ObservableField<>();
    public ObservableField<String> fineIn = new ObservableField<>();
    public ObservableField<String> fineOut = new ObservableField<>();
    public ObservableField<String> poorIn = new ObservableField<>();
    public ObservableField<String> poorOut = new ObservableField<>();
    public ObservableField<String> balance = new ObservableField<>();
    public ObservableField<String> pic = new ObservableField<>();
    public ObservableField<String> itemName = new ObservableField<>();
    public ObservableField<String> packaging = new ObservableField<>();
    public ObservableField<String> collection = new ObservableField<>();
    public ObservableField<String> dateExpired = new ObservableField<>();
    private ObservableBoolean empty = new ObservableBoolean(true);
    public ObservableField<InventoryJournalBatch> listBatch = new ObservableField<>();

    private InventoryJournalHandler inventoryJournalHandler;
    private Disposable inventoryJournalDisposable;
    private final CustomDialog dialog;
    private final DialogInventoryJournalBatchViewModel viewModel;
    ArrayList<Integer> Numbers = new ArrayList<Integer>();
    HashSet<Integer> hashSetNumbers = new HashSet<Integer>(Numbers);

    private final DialogKartubarangSelectBatchBinding binding;

    public ObservableBoolean getEmpty() {
        return empty;
    }

    public void setEmpty(ObservableBoolean empty) {
        this.empty = empty;
    }

    public InventoryJournalViewModel(Context context, InventoryJournalHandler inventoryJournalHandler) {
        super(context);
        this.inventoryJournalHandler = inventoryJournalHandler;

        binding =
                DialogKartubarangSelectBatchBinding.inflate(LayoutInflater.from(context), null);

        viewModel = new DialogInventoryJournalBatchViewModel();

        dialog = CustomDialog.get(context)
                .title("Select Batch")
                .fullscreen()
                .addView(binding.getRoot())
                .cancelable(true);
    }

    public void refresh(String branch, String itemName) {
        if (inventoryJournalHandler != null)
            inventoryJournalHandler.onStartGetInventoryJournal();

        if (inventoryJournalDisposable != null && !inventoryJournalDisposable.isDisposed())
            getCompositeDisposable().remove(inventoryJournalDisposable);

        inventoryJournalDisposable = getInventoryJournal(branch, itemName)
                .subscribe(inventoryJournalList -> {
                    if (inventoryJournalHandler != null)
                        inventoryJournalHandler.onGetInventoryJournal(inventoryJournalList);

                    Log.d(TAG, "refresh: "+inventoryJournalList.get(0).getDOCUMENT());



                }, throwable -> {
                    if (inventoryJournalHandler != null)
                        inventoryJournalHandler.onError(throwable);
                    Log.e(TAG, "refresh: "+throwable.toString());
                });

        getCompositeDisposable().add(inventoryJournalDisposable);

    }


    public void onAddBatchClick() {
        if (inventoryJournalHandler != null)
            inventoryJournalHandler.goToSelectBatch();
    }


    public Observable<List<InventoryJournal>> getInventoryJournal(String branch, String itemName) {
        String url = "http://192.168.43.205/my-warehouse-web/public/api/getAllDataInventoryJournal";

        Map<String, String> param = new HashMap<>();


        return Rx2AndroidNetworking.get(url)
                .build()
                .getJSONObjectObservable()
                .map(jsonObject -> {
                    List<InventoryJournal> listInventoryJournal = new ArrayList<>();
                    JSONArray dataRow = jsonObject.getJSONArray("data");



                    for (int i = 0; i < dataRow.length(); i++) {
                        try {
                            JSONObject obj = dataRow.getJSONObject(i);

                            InventoryJournal inventoryJournal = new InventoryJournal();
                            inventoryJournal.setDATE(obj.getString("date"));
                            inventoryJournal.setCUSTOMER(obj.getString("customer"));
                            inventoryJournal.setDOCUMENT(obj.getString("document"));
                            inventoryJournal.setFINE_IN(obj.getString("fine_in"));
                            inventoryJournal.setFINE_OUT(obj.getString("fine_out"));
                            inventoryJournal.setPOOR_IN(obj.getString("poor_in"));
                            inventoryJournal.setPOOR_OUT(obj.getString("poor_out"));
                            inventoryJournal.setPIC(obj.getString("pic"));
                            inventoryJournal.setITEM_NAME(obj.getString("item_name"));
                            inventoryJournal.setPACKAGING(obj.getString("packaging"));
                            inventoryJournal.setCOLLECTION(obj.getString("collection"));
                            inventoryJournal.setDATE_EXPIRED(obj.getString("date_expired"));

                            int in = Integer.parseInt(inventoryJournal.getFINE_IN());
                            int out = Integer.parseInt(inventoryJournal.getFINE_OUT());
                            int result = in - out;
                            inventoryJournal.setRESULT(String.valueOf(result));

                            int balance = 0;
                            if (i == 0) {
                                balance += result;
                                inventoryJournal.setBALANCE(String.valueOf(balance));
                            } else {
                                int newBalance = Integer.parseInt(listInventoryJournal.get(i-1).getBALANCE());
                                newBalance += result;
                                inventoryJournal.setBALANCE(String.valueOf(newBalance));
                            }

                            listInventoryJournal.add(inventoryJournal);
                        }
                        catch (JSONException e) {
                            Log.e(TAG, "getInventoryJournal: "+e.getMessage() );
                        }
                    }
                    return listInventoryJournal;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
