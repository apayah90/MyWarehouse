package com.pujiy.mywarehouse.inventoryjournal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.androidnetworking.error.ANError;
import com.androidnetworking.utils.Utils;
import com.pujiy.mywarehouse.CustomDialog;
import com.pujiy.mywarehouse.base.BaseActivity;
import com.pujiy.mywarehouse.base.BaseViewModel;
import com.pujiy.mywarehouse.data.InventoryJournal;
import com.pujiy.mywarehouse.data.InventoryJournalBatch;
import com.pujiy.mywarehouse.databinding.DialogKartubarangSelectBatchBinding;
import com.pujiy.mywarehouse.inventoryjournal.dialog.DialogInventoryJournalBatchViewModel;
import com.pujiy.mywarehouse.inventoryjournal.interfaces.InventoryJournalHandler;
import com.pujiy.mywarehouse.inventoryjournal.selectbatch.SelectBatchActivity;
import com.pujiy.mywarehouse.testbaseadapter.DialogBatchViewModel;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InventoryJournalViewModel extends BaseViewModel implements  DialogBatchViewModel.Listener {

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
    public ObservableField<String> stokAwal = new ObservableField<>();
    public ObservableField<String> stokAwalBad = new ObservableField<>();
    public ObservableField<Boolean> badStock = new ObservableField<>(false);
    public ObservableField<String> productIteno = new ObservableField<>();
    public ObservableField<String> productItnam = new ObservableField<>();
    public ObservableField<String> productUndes = new ObservableField<>();
    public ObservableField<String> branch = new ObservableField<>();
    public ObservableField<String> totalGoodStockIn = new ObservableField<>();
    public ObservableField<String> totalGoodStockOut = new ObservableField<>();
    public ObservableField<String> totalBadStockIn = new ObservableField<>();
    public ObservableField<String> totalBadStockOut = new ObservableField<>();
    public ObservableField<String> totalGoodStockSisa = new ObservableField<>();
    public ObservableField<String> totalBadStockSisa = new ObservableField<>();
    public ObservableField<String> selectedBatch = new ObservableField<>();


    public String totalInGood = "321";
    public String totalOutGood = "26";
    public String totalSisaGood = "295";
    public String totalInBad = "15.0";
    public String totalOutBad = "10.0";
    public String totalSisaBad = "5.0";

    public ArrayList<String> batchs = new ArrayList<>();
    public ArrayList<String> batchsUnique = new ArrayList<>();

    public int lastBalance;

    private InventoryJournalHandler inventoryJournalHandler;
    private Disposable inventoryJournalDisposable;
    private final CustomDialog dialog;
    private final DialogBatchViewModel viewModel;
    ArrayList<Integer> Numbers = new ArrayList<Integer>();
    HashSet<Integer> hashSetNumbers = new HashSet<Integer>(Numbers);
    List<String> inventoryJournalsCollectionList = new ArrayList<>();
    List<String> kartuBarangBatches = new ArrayList<>();

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

        viewModel = new DialogBatchViewModel(context, this);

        dialog = CustomDialog.get(context)
                .title("Select Batch")
                .fullscreen()
                .addView(binding.getRoot())
                .cancelable(true);

        binding.setVm(viewModel);

        binding.rvBatch.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

    }

//    public void onSearch(CharSequence s, int start, int before, int count) {
//        productIteno.set(s.toString());
//        Toast.makeText(((BaseActivity) getContext()), ""+s.toString(), Toast.LENGTH_SHORT).show();
//
//    }

    public void refresh() {
        if (inventoryJournalHandler != null)
            inventoryJournalHandler.onStartGetInventoryJournal();

        if (inventoryJournalDisposable != null && !inventoryJournalDisposable.isDisposed())
            getCompositeDisposable().remove(inventoryJournalDisposable);

        inventoryJournalDisposable = getInventoryJournal()
                .subscribe(inventoryJournalList -> {
                    if (inventoryJournalHandler != null)
//                        inventoryJournals.addAll(inventoryJournalList);
                        inventoryJournalHandler.onGetInventoryJournal(inventoryJournalList);
                    productItnam.set(inventoryJournalList.get(0).getITEM_NO() + " - " +
                            inventoryJournalList.get(0).getITEM_NAME());
                    productUndes.set(inventoryJournalList.get(0).getPACKAGING());




//                    List<DataObj> arr_filteredlist = new ArrayList<>();
//                    for(DataObj e : arr_datalist) {
//                        if(e.i_age > 32 && e.str_address.equals("city2")) {
//                            arr_filteredlist.add(e);
//                        }
//                    }


//                    stokAwal.set(inventoryJournalList.get(0).getIN_GOOD());
//                    stokAwalBad.set(kartuBarangList.get(0).getIN_BAD());
//                    stokAwal.set(inventoryJournalList.get(0).getRESULT());
//                    stokAwalBad.set(inventoryJournalList.get(0).getRESULTBAD());



                    Log.d(TAG, "refresh: "+inventoryJournalList.get(0).getDOCUMENT());
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    getBatchList(inventoryJournalList);


                }, throwable -> {
                    if (inventoryJournalHandler != null)
                        inventoryJournalHandler.onError(throwable);
                    Log.e(TAG, "refresh: "+throwable.toString());
                });

        getCompositeDisposable().add(inventoryJournalDisposable);

    }

//    public void filterBatch() {

//        List<InventoryJournal> batchs = new ArrayList<>();

//

//        HashSet<String> hashSetStrings = new HashSet<String>(inventoryJournalsCollectionList);
//        for (String strBatch : hashSetStrings)
//            kartuBarangBatches.add(strBatch);
//            Log.d(TAG, "filterBatch: "+strBatch);
//    }

    public void onAddBatchClick(View v) {
        viewModel.showStockBatch(batchsUnique);

        dialog.show();
    }


    public void getBatchList(List<InventoryJournal> kartuBarangList) {

        HashSet<String> hashSetBatchs
                = new HashSet<String>(batchs);

        // iterate through HashSet
        batchsUnique.addAll(hashSetBatchs);

//        if (kartuBarangHandler != null)
//            kartuBarangHandler.onGetKartuBarangBatch(batchsUnique, kartuBarangList);

        for (String strBatchUnique : batchsUnique)
            Log.d(TAG, "getBatchList: " + strBatchUnique);

    }


    public void refreshByBatch() {
        if (inventoryJournalHandler != null)
            inventoryJournalHandler.onStartGetInventoryJournal();

        if (inventoryJournalDisposable != null && !inventoryJournalDisposable.isDisposed())
            getCompositeDisposable().remove(inventoryJournalDisposable);

        inventoryJournalDisposable = getKartuBarangBatch()
                .subscribe(inventoryJournalByBatchList -> {
                    if (inventoryJournalDisposable != null)
                        inventoryJournalHandler.onGetInventoryJournal(inventoryJournalByBatchList);
                    productItnam.set(inventoryJournalByBatchList.get(0).getITEM_NO() + " - " +
                            inventoryJournalByBatchList.get(0).getITEM_NAME());
                    productUndes.set(inventoryJournalByBatchList.get(0).getPACKAGING());

                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    getBatchList(inventoryJournalByBatchList);
                    Log.d(TAG, "refreshByBatch: " + inventoryJournalByBatchList.size());
                }, throwable -> {
                    if (inventoryJournalHandler != null)
                        inventoryJournalHandler.onError(throwable);
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "refresh: " + throwable);
                });

        getCompositeDisposable().add(inventoryJournalDisposable);
    }

    public Observable<List<InventoryJournal>> getKartuBarangBatch() {


        String url = "http://192.168.43.205/my-warehouse-web/public/api/collection";




        return Rx2AndroidNetworking.get(url)
                .build()
                .getJSONObjectObservable()
                .map(jsonObject -> {

                    List<InventoryJournal> listKartuBarangBatch = new ArrayList<>();
                    Log.d(TAG, "getKartuBarangBatch: "+selectedBatch.get());
                    jsonObject = jsonObject.getJSONObject("data");
                    JSONArray batchRow = jsonObject.getJSONArray("212019A");

                    for (int i = 0; i < batchRow.length(); i++) {
                        try {
                            InventoryJournal inventoryJournal = new InventoryJournal();
                            JSONObject obj = batchRow.getJSONObject(i);

                            inventoryJournal.setDATE(obj.getString("date"));
                            inventoryJournal.setDOCUMENT(obj.getString("document"));
                            inventoryJournal.setDOCTYPE(obj.getString("doc_type"));
//                            inventoryJournal.setFINE_IN(obj.getString("fine_in"));
//                            inventoryJournal.setFINE_OUT(obj.getString("fine_out"));
//                            inventoryJournal.setPOOR_IN(obj.getString("poor_in"));
//                            inventoryJournal.setPOOR_OUT(obj.getString("poor_out"));
                            inventoryJournal.setITEM_NAME(obj.getString("item_name"));
                            inventoryJournal.setPACKAGING(obj.getString("packaging"));
                            inventoryJournal.setCOLLECTION(obj.getString("collection"));
                            inventoryJournal.setDATE_EXPIRED(obj.getString("date_expired"));

                            if (obj.isNull("customer")) {
                                inventoryJournal.setCUSTOMER("-");
                            } else {
                                inventoryJournal.setCUSTOMER(obj.getString("customer"));
                            }

                            if (obj.isNull("pic")) {
                                inventoryJournal.setPIC("-");
                            } else {
                                inventoryJournal.setPIC(obj.getString("pic"));
                            }

                            if (obj.isNull("fine_in")) {
                                inventoryJournal.setFINE_IN("0");
                            } else {
                                inventoryJournal.setFINE_IN(obj.getString("fine_in"));
                            }

                            if (obj.isNull("fine_out")) {
                                inventoryJournal.setFINE_OUT("0");
                            } else {
                                inventoryJournal.setFINE_OUT(obj.getString("fine_out"));
                            }

                            if (obj.isNull("poor_in")) {
                                inventoryJournal.setPOOR_IN("0");
                            } else {
                                inventoryJournal.setPOOR_IN(obj.getString("poor_in"));
                            }

                            if (obj.isNull("poor_out")) {
                                inventoryJournal.setPOOR_OUT("0.00");
                            } else {
                                inventoryJournal.setPOOR_OUT(obj.getString("poor_out"));
                            }

                            String batch = inventoryJournal.getCOLLECTION();
                            batchs.add(batch);


                            // Calculate
                            int in = Integer.parseInt(inventoryJournal.getFINE_IN());
                            int out = Integer.parseInt(inventoryJournal.getFINE_OUT());
                            double inBad = Double.parseDouble(inventoryJournal.getPOOR_IN());
                            double outBad = Double.parseDouble(inventoryJournal.getPOOR_OUT());
                            int result = in - out; // -> -1 / 1
                            inventoryJournal.setRESULT(String.valueOf(result));

                            int balance = 0;
                            double balanceBad = 0.0;
                            int totalBalanceGoodStock;
                            double totalBalanceBadStock;
                            int previousBalanceIn;
                            int previousBalanceOut;
                            double previousBalanceInBadStock = 0.0;
                            double previousBalanceOutBadStock = 0.0;

                            // Calculate SA RET

                            if (inventoryJournal.getDOCTYPE().equals("SA")) {
                                inventoryJournal.setRESULT(String.valueOf(in));
                                inventoryJournal.setRESULTBAD(String.valueOf(inBad));
                                inventoryJournal.setBALANCE(String.valueOf(in));
                                inventoryJournal.setBALANCEBAD(String.valueOf(inBad));
                                stokAwal.set(inventoryJournal.getBALANCE());
                                stokAwalBad.set(inventoryJournal.getBALANCEBAD());

                            }

                            if(inventoryJournal.getDOCTYPE().equals("RETC")) {

                                previousBalanceIn = Integer.parseInt(listKartuBarangBatch.get(i - 1).getBALANCE());
                                previousBalanceInBadStock = Double.parseDouble(listKartuBarangBatch.get(i - 1).getBALANCEBAD());
                                totalBalanceGoodStock = previousBalanceIn + in;
                                inventoryJournal.setBALANCE(String.valueOf(totalBalanceGoodStock));
                                totalBalanceBadStock = previousBalanceInBadStock + inBad;
                                inventoryJournal.setBALANCEBAD(String.valueOf(totalBalanceBadStock));
                                inventoryJournal.setRESULT(String.valueOf(in));
                                inventoryJournal.setRESULTBAD(String.valueOf(inBad));

                                if(inBad != 0 || outBad != 0) {
                                    inventoryJournal.setISBADSTOCK("1");

                                }
                            }

                            // Calculate INV RETCU
                            if(inventoryJournal.getDOCTYPE().equals("INV") ||
                                    inventoryJournal.getDOCTYPE().equals("RETCU")) {


                                previousBalanceOut = Integer.parseInt(listKartuBarangBatch.get(i - 1).getBALANCE());
                                previousBalanceOutBadStock = Double.parseDouble(listKartuBarangBatch.get(i - 1).getBALANCEBAD());



                                if(inBad != 0 || outBad != 0) {
                                    inventoryJournal.setISBADSTOCK("1");

                                }
                                totalBalanceGoodStock = previousBalanceOut - out;
                                inventoryJournal.setBALANCE(String.valueOf(totalBalanceGoodStock));
                                totalBalanceBadStock = previousBalanceOutBadStock - outBad;
                                inventoryJournal.setBALANCEBAD(String.valueOf(totalBalanceBadStock));
                                inventoryJournal.setRESULT(String.valueOf(out));
                                inventoryJournal.setRESULTBAD(String.valueOf(outBad));
                            }

                            if (i == batchRow.length()) {
                                lastBalance = Integer.parseInt(inventoryJournal.getBALANCE());
                            }

                            inventoryJournalsCollectionList.add(inventoryJournal.getCOLLECTION());
                            listKartuBarangBatch.add(inventoryJournal);
                        } catch (JSONException e) {
                            Log.e(TAG, "getKartuBarang: JSONError on index: " + i + "; " +
                                    e.getMessage());
                        }
                    }

                    return listKartuBarangBatch;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public void onSearchKartuBarangClick() {

//        InventoryJournalHandler.onChangedType(badStock.get());
        refresh();
        Toast.makeText(getContext(), "Mohon Tunggu...", Toast.LENGTH_SHORT).show();

    }

    public void onGoodStockClick() {
        badStock.set(false);

    }

    public void onBadStockClick() {
        badStock.set(true);

    }


//    public void onAddBatchClick() {
//        if (inventoryJournalHandler != null)
//            inventoryJournalHandler.goToSelectBatch();
//    }


    public Observable<List<InventoryJournal>> getInventoryJournal() {
        String url = "http://192.168.43.205/my-warehouse-web/public/api/showFlashdisk";

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
                            inventoryJournal.setDOCUMENT(obj.getString("document"));
                            inventoryJournal.setDOCTYPE(obj.getString("doc_type"));
//                            inventoryJournal.setFINE_IN(obj.getString("fine_in"));
//                            inventoryJournal.setFINE_OUT(obj.getString("fine_out"));
//                            inventoryJournal.setPOOR_IN(obj.getString("poor_in"));
//                            inventoryJournal.setPOOR_OUT(obj.getString("poor_out"));
                            inventoryJournal.setITEM_NAME(obj.getString("item_name"));
                            inventoryJournal.setPACKAGING(obj.getString("packaging"));
                            inventoryJournal.setCOLLECTION(obj.getString("collection"));
                            inventoryJournal.setDATE_EXPIRED(obj.getString("date_expired"));

                            if (obj.isNull("customer")) {
                                inventoryJournal.setCUSTOMER("-");
                            } else {
                                inventoryJournal.setCUSTOMER(obj.getString("customer"));
                            }

                            if (obj.isNull("pic")) {
                                inventoryJournal.setPIC("-");
                            } else {
                                inventoryJournal.setPIC(obj.getString("pic"));
                            }

                            if (obj.isNull("fine_in")) {
                                inventoryJournal.setFINE_IN("0");
                            } else {
                                inventoryJournal.setFINE_IN(obj.getString("fine_in"));
                            }

                            if (obj.isNull("fine_out")) {
                                inventoryJournal.setFINE_OUT("0");
                            } else {
                                inventoryJournal.setFINE_OUT(obj.getString("fine_out"));
                            }

                            if (obj.isNull("poor_in")) {
                                inventoryJournal.setPOOR_IN("0");
                            } else {
                                inventoryJournal.setPOOR_IN(obj.getString("poor_in"));
                            }

                            if (obj.isNull("poor_out")) {
                                inventoryJournal.setPOOR_OUT("0.00");
                            } else {
                                inventoryJournal.setPOOR_OUT(obj.getString("poor_out"));
                            }

                            String batch = inventoryJournal.getCOLLECTION();
                            batchs.add(batch);

                            // Calculate
                            int in = Integer.parseInt(inventoryJournal.getFINE_IN());
                            int out = Integer.parseInt(inventoryJournal.getFINE_OUT());
                            double inBad = Double.parseDouble(inventoryJournal.getPOOR_IN());
                            double outBad = Double.parseDouble(inventoryJournal.getPOOR_OUT());
                            int result = in - out; // -> -1 / 1
                            inventoryJournal.setRESULT(String.valueOf(result));

                            int balance = 0;
                            double balanceBad = 0.0;
                            int totalBalanceGoodStock;
                            double totalBalanceBadStock;
                            int previousBalanceIn;
                            int previousBalanceOut;
                            double previousBalanceInBadStock = 0.0;
                            double previousBalanceOutBadStock = 0.0;

                            // Calculate SA RET

                            if (inventoryJournal.getDOCTYPE().equals("SA")) {
                                inventoryJournal.setRESULT(String.valueOf(in));
                                inventoryJournal.setRESULTBAD(String.valueOf(inBad));
                                inventoryJournal.setBALANCE(String.valueOf(in));
                                inventoryJournal.setBALANCEBAD(String.valueOf(inBad));
                                stokAwal.set(inventoryJournal.getBALANCE());
                                stokAwalBad.set(inventoryJournal.getBALANCEBAD());

                            }

                            if(inventoryJournal.getDOCTYPE().equals("RETC")) {

                                previousBalanceIn = Integer.parseInt(listInventoryJournal.get(i - 1).getBALANCE());
                                previousBalanceInBadStock = Double.parseDouble(listInventoryJournal.get(i - 1).getBALANCEBAD());
                                totalBalanceGoodStock = previousBalanceIn + in;
                                inventoryJournal.setBALANCE(String.valueOf(totalBalanceGoodStock));
                                totalBalanceBadStock = previousBalanceInBadStock + inBad;
                                inventoryJournal.setBALANCEBAD(String.valueOf(totalBalanceBadStock));
                                inventoryJournal.setRESULT(String.valueOf(in));
                                inventoryJournal.setRESULTBAD(String.valueOf(inBad));

                                if(inBad != 0 || outBad != 0) {
                                    inventoryJournal.setISBADSTOCK("1");

                                }
                            }

                            // Calculate INV RETCU
                            if(inventoryJournal.getDOCTYPE().equals("INV") ||
                                    inventoryJournal.getDOCTYPE().equals("RETCU")) {


                                    previousBalanceOut = Integer.parseInt(listInventoryJournal.get(i - 1).getBALANCE());
                                    previousBalanceOutBadStock = Double.parseDouble(listInventoryJournal.get(i - 1).getBALANCEBAD());



                                if(inBad != 0 || outBad != 0) {
                                    inventoryJournal.setISBADSTOCK("1");

                                }
                                totalBalanceGoodStock = previousBalanceOut - out;
                                inventoryJournal.setBALANCE(String.valueOf(totalBalanceGoodStock));
                                totalBalanceBadStock = previousBalanceOutBadStock - outBad;
                                inventoryJournal.setBALANCEBAD(String.valueOf(totalBalanceBadStock));
                                inventoryJournal.setRESULT(String.valueOf(out));
                                inventoryJournal.setRESULTBAD(String.valueOf(outBad));
                            }

                            if (i == dataRow.length()) {
                                lastBalance = Integer.parseInt(inventoryJournal.getBALANCE());
                            }

                            inventoryJournalsCollectionList.add(inventoryJournal.getCOLLECTION());
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

    @Override
    public void onBatchSelected(String batch) {
        selectedBatch.set(batch);
        refreshByBatch();
        dialog.dismiss();
    }
}
