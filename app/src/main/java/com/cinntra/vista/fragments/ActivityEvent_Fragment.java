package com.cinntra.vista.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinntra.vista.EasyPrefs.Prefs;
import com.cinntra.vista.R;
import com.cinntra.vista.adapters.ActivityEventsAdapter;
import com.cinntra.vista.databinding.FragmentEventsBinding;
import com.cinntra.vista.globals.Globals;
import com.cinntra.vista.interfaces.OnRefresh;
import com.cinntra.vista.model.EventResponse;
import com.cinntra.vista.model.EventValue;
import com.cinntra.vista.model.QuotationResponse;
import com.cinntra.vista.webservices.NewApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityEvent_Fragment extends Fragment implements OnRefresh {

    ArrayList<EventValue> alleventlist;
    ActivityEventsAdapter adapter;
    String id;

    public ActivityEvent_Fragment(String id) {
        this.id = id;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    FragmentEventsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEventsBinding.inflate(inflater, container, false);

        //    loadData();
        callApi(id);
        return binding.getRoot();
    }


    @Override
    public void onRefresh() {
        Log.e("TAG==>Refresh", "onRefresh: check" );
        callApi(id);
    }

    private void callApi(String id) {
        alleventlist = new ArrayList<>();
        EventValue eventValue = new EventValue();
        eventValue.setEmp(Integer.valueOf(Prefs.getString(Globals.EmployeeID, "")));
//        eventValue.setEmp(Integer.valueOf((Globals.TeamEmployeeID)));

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Emp", Prefs.getString(Globals.EmployeeID, ""));
        jsonObject.addProperty("date", Globals.CurrentSelectedDate);

//        Call<EventResponse> call = NewApiClient.getInstance().getApiService(this).getallevent(eventValue);
        Call<EventResponse> call = NewApiClient.getInstance().getApiService(requireContext()).getCalendarData(jsonObject);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {

                if (response.body().getStatus() == 200 && response.body() != null) {
                    if (response.body().getData().size() > 0) {
                        alleventlist.clear();
                        alleventlist.addAll(response.body().getData());
                        setAdapter();
                    } else
                        Toast.makeText(getContext(), "No data found", Toast.LENGTH_LONG).show();
                } else {
                    //Globals.ErrorMessage(CreateContact.this,response.errorBody().toString());
                    Gson gson = new GsonBuilder().create();
                    QuotationResponse mError = new QuotationResponse();
                    try {
                        String s = response.errorBody().string();
                        mError = gson.fromJson(s, QuotationResponse.class);
                        Toast.makeText(getActivity(), mError.getError().getMessage().getValue(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        //handle failure to read error
                    }
                    //Toast.makeText(CreateContact.this, msz, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public ArrayList<EventValue> filter(String text) {

        ArrayList<EventValue> templist = new ArrayList<>();
        templist.clear();

        for (EventValue st : alleventlist) {
            if (st.getOppId().toString().equalsIgnoreCase(id)) {
                if (st.getType().equals(text)) {
                    templist.add(st);
                }
            }
        }

        return templist;

    }

    private void setAdapter() {
        binding.eventList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new ActivityEventsAdapter(getActivity(), filter("Event"));
        binding.eventList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() == 0) {
            binding.noDatafound.setVisibility(View.VISIBLE);
        } else {
            binding.noDatafound.setVisibility(View.GONE);
        }
    }

    private ArrayList<EventValue> geEvents(ArrayList<EventValue> list) {

        ArrayList<EventValue> events = new ArrayList<>();
        for (EventValue event : list) {


            if (event.getType() == "Task") {
                // &&Globals.CurrentSelectedDate.equalsIgnoreCase(event.getDateFrom())

                try {
                    String to = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    Date FromDate = new SimpleDateFormat("yyyy-MM-dd").parse(event.getFrom().trim());
                    Date ToDate = new SimpleDateFormat("yyyy-MM-dd").parse(event.getTo().trim());
                    Date ToDayDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
                    boolean dateStatus = isDateInBetweenIncludingEndPoints(FromDate, ToDate, ToDayDate);
                    if (dateStatus)
                        events.add(event);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return events;
    }

    public static boolean isDateInBetweenIncludingEndPoints(final Date min, final Date max, final Date date) {
        return !(date.before(min) || date.after(max));
    }
}