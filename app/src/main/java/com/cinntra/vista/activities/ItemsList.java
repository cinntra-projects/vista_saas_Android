package com.cinntra.vista.activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinntra.vista.R;
import com.cinntra.vista.adapters.ItemsAdapter;
import com.cinntra.vista.cart.adapter.ItemsListForCartAdapter;
import com.cinntra.vista.databinding.ActivityItemBinding;
import com.cinntra.vista.globals.Globals;
import com.cinntra.vista.globals.MainBaseActivity;
import com.cinntra.vista.model.DocumentLines;
import com.cinntra.vista.model.ItemCategoryData;
import com.cinntra.vista.model.ItemResponse;
import com.cinntra.vista.model.QuotationResponse;
import com.cinntra.vista.viewModel.ItemViewModel;
import com.cinntra.vista.webservices.NewApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemsList extends MainBaseActivity implements View.OnClickListener {

    LinearLayoutManager layoutManager;
    int skipSize = 20;

    ArrayList<DocumentLines> AllitemsList;

  //  ItemsAdapter adapter;

    ItemsListForCartAdapter itemsListForCartAdapter;
    //private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean recallApi = true;
    int currentPage = 0;
    int pageno = 1;

    ActivityItemBinding binding;

    Boolean isScrollingpage = false;

    int custID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // ButterKnife.bind(this);
        binding.quotesHeader.filterView.setVisibility(View.GONE);
        binding.quotesHeader.newQuatos.setVisibility(View.GONE);
        AllitemsList = new ArrayList<>();

        binding.loader.loader.setVisibility(View.GONE);

        if (getIntent() != null) {
            int custID = getIntent().getIntExtra("CategoryID", 0);

            callItemListApi(custID);
        }

        setDefaults();

        eventSearchManager();

        setAdapter();


        //todo pagination for list..
        binding.itemsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                if (Globals.checkInternet(getApplicationContext())) {
                    if (isScrollingpage && lastCompletelyVisibleItemPosition == AllitemsList.size() - 2) {
                        pageno++;
                        Log.e("page--->", String.valueOf(pageno));
                        callItemListAllPageApi(custID);

                        isScrollingpage = false;
                    }
                }

            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) { //it means we are scrolling
                    isScrollingpage = true;
                }
            }

        });


       /* binding.nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.

                    if (Globals.checkInternet(ItemsList.this) && recallApi) {

                        binding.idPBLoading.setVisibility(View.VISIBLE);
                        binding.loader.loader.setVisibility(View.VISIBLE);
                        pageno++;
                        AllitemsList.clear();
                        callApi(binding.loader.loader, fromwhere);

                    }


                }

            }
        });*///todo comment by me--due to no need of pagination


    }

    @Override
    public void onBackPressed() {

        if (binding.quotesHeader.mainHeaderLay.getVisibility() == View.GONE) {
            binding.quotesHeader.searchLay.setVisibility(View.GONE);
            binding.quotesHeader.mainHeaderLay.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }

    private void eventSearchManager() {
        binding.quotesHeader.searchView.setBackgroundColor(Color.parseColor("#00000000"));
        binding.quotesHeader.searchLay.setVisibility(View.VISIBLE);
        binding.quotesHeader.searchView.setVisibility(View.VISIBLE);

        binding.quotesHeader.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.quotesHeader.searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (itemsListForCartAdapter != null) {
                    itemsListForCartAdapter.filter(newText);
                }
                return true;
            }
        });

    }

    String fromwhere;

    private void setDefaults() {
        binding.quotesHeader.headTitle.setText(getResources().getString(R.string.items));
        binding.quotesHeader.backPress.setOnClickListener(this);
        binding.quotesHeader.search.setOnClickListener(this);
        fromwhere = String.valueOf(getIntent().getExtras().getInt("CategoryID"));
        // Toast.makeText(this,fromwhere,Toast.LENGTH_SHORT).show();
        if (Globals.checkInternet(getApplicationContext())) {
            /*pageno = 1;
            recallApi = true;
            AllitemsList.clear();
            callApi(binding.loader.loader, fromwhere);*///todo comment

//            callItemListApi(custID);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.search:
                binding.quotesHeader.mainHeaderLay.setVisibility(View.GONE);
                binding.quotesHeader.searchLay.setVisibility(View.VISIBLE);

                binding.quotesHeader.searchView.setIconifiedByDefault(true);
                binding.quotesHeader.searchView.setFocusable(true);
                binding.quotesHeader.searchView.setIconified(false);
                binding.quotesHeader.searchView.requestFocusFromTouch();
                break;

        }
    }



    public void callItemListApi(int custID){
        binding.loader.loader.setVisibility(View.VISIBLE);

        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("PageNo", pageno);
//        jsonObject.addProperty("maxItem", 20);

        jsonObject.addProperty("CatID", custID);
        jsonObject.addProperty("PageNo",pageno);
        jsonObject.addProperty("maxItem",10);


        Call<ItemResponse> call = NewApiClient.getInstance().getApiService(this).ItemsList(jsonObject);
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200) {
                            binding.loader.loader.setVisibility(View.GONE);
                            List<DocumentLines> itemsList = new ArrayList<>();
                            if (response.body().getValue().size() > 0 && response.body().getValue() != null) {
                                binding.noDatafound.setVisibility(View.GONE);
                                itemsList = response.body().getValue();
                                AllitemsList.clear();
                                AllitemsList.addAll(itemsList);
                                itemsListForCartAdapter.AllData(AllitemsList);
                                itemsListForCartAdapter.notifyDataSetChanged();

                            } else {
                                AllitemsList.clear();
                                AllitemsList.addAll(itemsList);
                                itemsListForCartAdapter.AllData(AllitemsList);
                                itemsListForCartAdapter.notifyDataSetChanged();
                                Globals.setmessage(getApplicationContext());
                                binding.noDatafound.setVisibility(View.VISIBLE);
                            }

                        }
                    } else if (response.body().getStatus() == 301) {
                        binding.loader.loader.setVisibility(View.GONE);
                        Gson gson = new GsonBuilder().create();
                        QuotationResponse mError = new QuotationResponse();
                        try {
                            String s = response.errorBody().string();
                            mError = gson.fromJson(s, QuotationResponse.class);
                            Toast.makeText(ItemsList.this, mError.getMessage(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            // handle failure to read error
                        }
                    } else {
                        binding.loader.loader.setVisibility(View.GONE);
                   /*     Gson gson = new GsonBuilder().create();
                        QuotationResponse mError = new QuotationResponse();
                        try {
                            String s = response.errorBody().string();
                            mError = gson.fromJson(s, QuotationResponse.class);
                            Toast.makeText(ItemsList.this, mError.getError().getMessage().getValue(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    binding.loader.loader.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                binding.loader.loader.setVisibility(View.GONE);
                Toast.makeText(ItemsList.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    public void callItemListAllPageApi(int custID){
        binding.loader.loader.setVisibility(View.VISIBLE);

        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("PageNo", pageno);
//        jsonObject.addProperty("maxItem", 20);

        jsonObject.addProperty("CatID", custID);
        jsonObject.addProperty("PageNo",pageno);
        jsonObject.addProperty("maxItem",10);


        Call<ItemResponse> call = NewApiClient.getInstance().getApiService(this).ItemsList(jsonObject);
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200) {
                            binding.loader.loader.setVisibility(View.GONE);
                            List<DocumentLines> itemsList = new ArrayList<>();
                            if (response.body().getValue().size() > 0 && response.body().getValue() != null) {

                                itemsList = response.body().getValue();

                                AllitemsList.addAll(itemsList);
                                itemsListForCartAdapter.AllData(AllitemsList);
                                itemsListForCartAdapter.notifyDataSetChanged();

                            } else {

                                AllitemsList.addAll(itemsList);
                                itemsListForCartAdapter.AllData(AllitemsList);
                                itemsListForCartAdapter.notifyDataSetChanged();


                            }

                        }
                    } else if (response.body().getStatus() == 301) {
                        binding.loader.loader.setVisibility(View.GONE);
                        Gson gson = new GsonBuilder().create();
                        QuotationResponse mError = new QuotationResponse();
                        try {
                            String s = response.errorBody().string();
                            mError = gson.fromJson(s, QuotationResponse.class);
                            Toast.makeText(ItemsList.this, mError.getMessage(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            // handle failure to read error
                        }
                    } else {
                        binding.loader.loader.setVisibility(View.GONE);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    binding.loader.loader.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                binding.loader.loader.setVisibility(View.GONE);
                Toast.makeText(ItemsList.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //todo bind adapter..
    void setAdapter() {
        layoutManager = new LinearLayoutManager(ItemsList.this, RecyclerView.VERTICAL, false);
        binding.itemsRecycler.setLayoutManager(layoutManager);
        itemsListForCartAdapter = new ItemsListForCartAdapter(AllitemsList);
        binding.itemsRecycler.setAdapter(itemsListForCartAdapter);

    }



    private void callApi(ProgressBar loader, String fromwhere) {
        ItemCategoryData id = new ItemCategoryData();
        id.setMaxItem(50);
        id.setPageNo(1);
        //  id.setCatID(fromwhere);
        ItemViewModel model = ViewModelProviders.of(this).get(ItemViewModel.class);
        model.getItemsList(loader, id).observe(this, new Observer<List<DocumentLines>>() {
            @Override
            public void onChanged(@Nullable List<DocumentLines> itemsList) {

                if (itemsList == null || itemsList.size() == 0) {
                    Globals.setmessage(getApplicationContext());
                    binding.noDatafound.setVisibility(View.VISIBLE);
                } else {
                    binding.noDatafound.setVisibility(View.GONE);
                    recallApi = itemsList.size() >= 50;
                    AllitemsList.addAll(itemsList);
                    itemsListForCartAdapter.notifyDataSetChanged();

                }
                binding.loader.loader.setVisibility(View.GONE);
            }
        });

    }


}