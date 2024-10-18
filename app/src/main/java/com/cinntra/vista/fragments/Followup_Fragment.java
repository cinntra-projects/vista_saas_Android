package com.cinntra.vista.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinntra.vista.EasyPrefs.Prefs;
import com.cinntra.vista.R;
import com.cinntra.vista.adapters.FollowUpAdapter;
import com.cinntra.vista.databinding.FragmentEventsBinding;
import com.cinntra.vista.globals.Globals;
import com.cinntra.vista.model.EventResponse;
import com.cinntra.vista.model.EventValue;
import com.cinntra.vista.model.NewEvent;
import com.cinntra.vista.model.QuotationResponse;
import com.cinntra.vista.model.SalesEmployeeItem;
import com.cinntra.vista.webservices.NewApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class Followup_Fragment extends Fragment {

//     @BindView(R.id.eventList)
//     RecyclerView eventList;
//     @BindView(R.id.loader)
//     SpinKitView loader;
//    @BindView(R.id.no_datafound)
//    ImageView no_datafound;
    private ArrayList<EventValue> TaskEventList;
    public Followup_Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Followup_Fragment newInstance(String param1, String param2) {
        Followup_Fragment fragment = new Followup_Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
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
        binding=FragmentEventsBinding.inflate(inflater,container,false);
        View v=inflater.inflate(R.layout.fragment_events, container, false);
       // ButterKnife.bind(this,v);
      //  loadData();
        callApi();
        return binding.getRoot();
    }
    private void callApi() {
        TaskEventList= new ArrayList<>();
        binding.loader.setVisibility(View.VISIBLE);

        SalesEmployeeItem eventValue = new SalesEmployeeItem();
        eventValue.setEmp(Prefs.getString(Globals.EmployeeID,""));
        eventValue.setDate(Globals.CurrentSelectedDate);


        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Emp", Prefs.getString(Globals.EmployeeID, ""));
        jsonObject.addProperty("date", Globals.CurrentSelectedDate);

        Call<EventResponse> call = NewApiClient.getInstance().getApiService(requireContext()).getCalendarData(jsonObject);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {

                if(response.code()==200&&response.body()!=null)
                {
                    if(response.body().getData().size()>0){
                        TaskEventList.clear();
                        TaskEventList.addAll(response.body().getData());
                        binding.noDatafound.setVisibility(View.GONE);
                        setAdapter();
                    }else{
                        binding.noDatafound.setVisibility(View.VISIBLE);

                    }

                }
                else
                {
                    //Globals.ErrorMessage(CreateContact.this,response.errorBody().toString());
                    Gson gson = new GsonBuilder().create();
                    QuotationResponse mError = new QuotationResponse();
                    try {
                        String s =response.errorBody().string();
                        mError= gson.fromJson(s, QuotationResponse.class);
                        Toast.makeText(getActivity(), mError.getError().getMessage().getValue(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        //handle failure to read error
                    }
                    //Toast.makeText(CreateContact.this, msz, Toast.LENGTH_SHORT).show();
                }
               binding. loader.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
               binding. loader.setVisibility(View.GONE);
            }

        });

    }


    private void setAdapter() {
        FollowUpAdapter adapter;
        if(filter("Followup").size()>0){
            binding.noDatafound.setVisibility(View.GONE);
           binding. eventList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
            adapter = new FollowUpAdapter(getActivity(), filter("Followup"));
           binding. eventList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            binding.noDatafound.setVisibility(View.VISIBLE);

        }



    }
    public ArrayList<EventValue> filter(String text ) {

        ArrayList<EventValue> templist= new ArrayList<>();
        templist.clear();
        for (EventValue st : TaskEventList) {

            if(st.getType().equals(text)) {

                templist.add(st);

            }


        }

        return templist;
    }
    private ArrayList<NewEvent> geEvents(ArrayList<NewEvent> list)
          {

      ArrayList<NewEvent> events = new ArrayList<>();
        for (NewEvent event :list
             ) {


            if(event.getType()== Globals.TYPE_EVENT) {
                // &&Globals.CurrentSelectedDate.equalsIgnoreCase(event.getDateFrom())

                try {
                    String to = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    Date FromDate = new SimpleDateFormat("yyyy-MM-dd").parse(event.getDateFrom().trim());
                    Date ToDate = new SimpleDateFormat("yyyy-MM-dd").parse(event.getDateTo().trim());
                    Date ToDayDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
                    boolean dateStatus = isDateInBetweenIncludingEndPoints(FromDate, ToDate, ToDayDate);
                   /* Log.e("DATE From",""+event.getDateFrom().trim());
                    Log.e("DATE TO",""+event.getDateTo().trim());
                    Log.e("DATE TODAY",""+to);
                    Log.e("DATE STATUS",""+dateStatus);*/

                    if (dateStatus)
                        events.add(event);

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        }

        return events;
    }

    public static boolean isDateInBetweenIncludingEndPoints(final Date min, final Date max, final Date date){
        return !(date.before(min) || date.after(max));
    }




    /********************** Manage List in local *******************************/

    private void loadData()
          {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Globals.TaskEventList, null);
        Type type = new TypeToken<ArrayList<NewEvent>>() {}.getType();
        TaskEventList = gson.fromJson(json, type);
        if (TaskEventList == null) {
            TaskEventList = new ArrayList<>();
        }

  /*      eventList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        eventList.setAdapter(new EventsAdapter(getActivity(), geEvents(TaskEventList)));*/
       }
}