package com.cinntra.vista.fragments;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cinntra.vista.EasyPrefs.Prefs;
import com.cinntra.vista.R;
import com.cinntra.vista.databinding.ActivityAddTaskDialogueLayoutBinding;
import com.cinntra.vista.globals.Globals;
import com.cinntra.vista.model.ContactPerson;
import com.cinntra.vista.model.ContactPersonData;
import com.cinntra.vista.model.CreateCalenderActivityRequest;
import com.cinntra.vista.model.EmployeeAllFilterPageModel;
import com.cinntra.vista.model.EventResponse;
import com.cinntra.vista.model.QuotationResponse;
import com.cinntra.vista.newapimodel.NewOpportunityRespose;
import com.cinntra.vista.webservices.NewApiClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAddTaskDialogue extends DialogFragment implements View.OnClickListener {

    String priority = "";
    String allday = "";
    String repeated = "";
    String progresstatus = "";

    String cardCode;
    String Participants;
    ArrayList<String> salesEmpParticipants = new ArrayList<>();
    final String[] selectedParticipantValue = new String[1];

    int t1hr, t1min;
    EventPrerioritySpinner eventPrerioritySpinner;
    EventTextSpinner eventTextSpinner;
    TaskProgressSpinner taskProgressSpinner;
    NewOpportunityRespose opportunityItem;
    ArrayList<String> categories = new ArrayList<>();
    ArrayList<Integer> circleimage = new ArrayList<>();
    ArrayList<String> progress_status = new ArrayList<>();
    final Calendar myCalendar = Calendar.getInstance();
    private AlarmManager alarmManager;
    private Calendar myTime;

    public ActivityAddTaskDialogue() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static ActivityAddTaskDialogue newInstance(String title) {
        ActivityAddTaskDialogue frag = new ActivityAddTaskDialogue();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    ActivityAddTaskDialogueLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ActivityAddTaskDialogueLayoutBinding.inflate(inflater, container, false);
        View v = inflater.inflate(R.layout.fragment_add_task, container);
        //  ButterKnife.bind(this,v);
        setDefaults();


//     loadData();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle b = getArguments();
            opportunityItem = (NewOpportunityRespose) b.getParcelable(Globals.OpportunityItem);
            cardCode = b.getString("card_code");
        }
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.loaderLayout.loader.setVisibility(View.GONE);
        binding.headerLayout.headTitle.setText("New Task");

        binding.headerLayout.backPress.setOnClickListener(this);
        binding.submitButton.setOnClickListener(this);
//        binding.simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    repeated = "";
//                    binding.spinnerview.setVisibility(View.INVISIBLE);
//                } else {
//                    binding.spinnerview.setVisibility(View.VISIBLE);
////                    repeated = "Repeated";
//                }
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_press) {
            getDialog().dismiss();
        } else if (v.getId() == R.id.submit_button) {

            String title = binding.titleText.getText().toString().trim();
            String dueDate = binding.dateValue.getText() != null ? binding.dateValue.getText().toString().trim() : "";
            String typeSpinner = binding.typeSpinner.getSelectedItem() != null ? binding.typeSpinner.getSelectedItem().toString().trim() : "";
            String assignTo = binding.assignToSpinner.getSelectedItem() != null ? binding.assignToSpinner.getSelectedItem().toString().trim() : "";

//            String date = binding.fromValue.getText().toString().trim();
//            String location = binding.addLocationText.getText().toString().trim();
//            String host = binding.hostText.getText().toString().trim();
            String desc = binding.descriptionText.getText().toString().trim();
//            String time = binding.timeValue.getText().toString().trim();


            if (validation(title, dueDate, typeSpinner, assignTo)) {

                CreateCalenderActivityRequest activityRequest = new CreateCalenderActivityRequest();

                activityRequest.setSourceID(opportunityItem.getId());
                activityRequest.setTitle(title);
                activityRequest.setDescription(desc);  //
                activityRequest.setFrom(Globals.convert_dd_MM_yyyy_to_yyyy_MM_dd(dueDate));
                activityRequest.setTo(Globals.convert_dd_MM_yyyy_to_yyyy_MM_dd(dueDate));
                activityRequest.setEmp(Prefs.getString(Globals.EmployeeID, ""));
                activityRequest.setCreateTime(Globals.getTCurrentTime()); //
                activityRequest.setCreateDate(Globals.getTodaysDatervrsfrmt());  //
                activityRequest.setType("Task");
                activityRequest.setSourceType("Opportunity");
                activityRequest.setParticipants(selectedParticipantValue[0]);
                activityRequest.setComment("");  //
                activityRequest.setSubject("");
                activityRequest.setTime(Globals.getTCurrentTime());
                activityRequest.setDocument("");  //
                activityRequest.setRelatedTo("hi");
                activityRequest.setLocation("");
                activityRequest.setHost("");
                activityRequest.setToTime(Globals.getTCurrentTime());
                activityRequest.setAllday("false");  //
                activityRequest.setName(opportunityItem.getCustomerName());
                activityRequest.setProgressStatus("WIP");
                activityRequest.setPriority("low");
                activityRequest.setRepeated(repeated);
                activityRequest.setLeadType("");


                if (Globals.checkInternet(getContext())) {
                    binding.loaderLayout.loader.setVisibility(View.VISIBLE);
                    callApi(activityRequest);
                }

            }
        }
    }

    private void callApi(CreateCalenderActivityRequest activityRequest) {

        Call<EventResponse> call = NewApiClient.getInstance().getApiService(requireContext()).createnewevent(activityRequest);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.body().getStatus() == 200) {
                    binding.loaderLayout.loader.setVisibility(View.GONE);

                    Toasty.success(getContext(), "Add Successfully", Toast.LENGTH_LONG).show();

                    getDialog().dismiss();

                } else {
                    binding.loaderLayout.loader.setVisibility(View.GONE);
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
                binding.loaderLayout.loader.setVisibility(View.GONE);
                Toasty.error(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validation(String title, String dueDate, String typeSpinner, String assignTo) {
        if (title.isEmpty()) {
            binding.titleText.setError(getResources().getString(R.string.title_error));
            return false;
        } else if (dueDate.isEmpty()) {
            binding.dateValue.setError(getResources().getString(R.string.duedate_error));
            return false;
        }
        else if (typeSpinner.isEmpty() || typeSpinner.equals("Select item")) {
            Toast.makeText(requireContext(), "Select Type", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setDefaults() {
        binding.headerLayout.add.setVisibility(View.GONE);
        circleimage.add(R.drawable.red_dot);
        circleimage.add(R.drawable.ic_green_dot);
        circleimage.add(R.drawable.yellow_dot);

//        categories.add("Repeat");
//        categories.add("Once");
//        categories.add("Daily");
//        categories.add("Weekly");
//        categories.add("Monthly");
//
//        progress_status.add("In Progress");
//        progress_status.add("Completed");

        // Find the views by ID
        Spinner typeSpinner = binding.typeSpinner;
        Spinner assignToSpinner = binding.assignToSpinner;
        LinearLayout assignToLayout = binding.assignToLayout;

        // Initialize the list of items for the type spinner
        List<String> typeItems = new ArrayList<>();
        typeItems.add("Select item"); // This is the hint
        typeItems.add("Employee");
        typeItems.add("Contact Person");


        // Create an adapter for the typeSpinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, typeItems);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

// Set up the listener for typeSpinner
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Check if "Select item" is selected
                if ("Select item".equals(selectedItem)) {
                    // Hide the assignToLayout (and therefore assignToSpinner)
                    assignToLayout.setVisibility(View.GONE);
                } else {
                    // Show the assignToLayout when any other item is selected
                    assignToLayout.setVisibility(View.VISIBLE);

                    // Call corresponding APIs based on the selected item
                    if ("Employee".equals(selectedItem)) {
                        if (Globals.checkInternet(getActivity())) {

                            callApiEmployeeList();

                        } else {
                            Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show();
                        }


                    } else if ("Contact Person".equals(selectedItem)) {
                        callApiContactPersonList(cardCode);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Handle cases where no item is selected
            }
        });


        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            binding.dateValue.setText(sdf.format(myCalendar.getTime()));
        };

        binding.dateValue.setOnClickListener(v -> {
            Globals.enableAllCalenderDateSelect(getContext(), binding.dateValue);
        });

    }

    private ArrayList<String> ContactEmployeesList;

    private void callApiContactPersonList(String cardCode) {

        ContactPersonData contactPersonData = new ContactPersonData();
        contactPersonData.setCardCode(cardCode);

        Call<ContactPerson> call = NewApiClient.getInstance().getApiService(requireContext()).contactemplist(contactPersonData);
        call.enqueue(new Callback<ContactPerson>() {
            @Override
            public void onResponse(Call<ContactPerson> call, Response<ContactPerson> response) {
                if (response.code() == 200) {
                    if (response.body().getData().size() > 0) {

                        ContactEmployeesList = new ArrayList<>();

                        int n = response.body().getData().size();


                        for (int i=0; i<n; i++) {
                            ContactEmployeesList.add(response.body().getData().get(i).getFirstName());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ContactEmployeesList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.assignToSpinner.setAdapter(adapter);


                    } else {
                        Toasty.error(getActivity(), response.body().getMessage());
                    }
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
            public void onFailure(Call<ContactPerson> call, Throwable t) {

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void callApiEmployeeList() {
        try {
            // Create the main payload object
            JsonObject payload = new JsonObject();
            payload.addProperty("SalesPersonCode", Prefs.getString(Globals.SalesEmployeeCode, ""));
            payload.addProperty("PageNo", 1);
            payload.addProperty("maxItem", "All");
            payload.addProperty("order_by_field", "id");
            payload.addProperty("order_by_value", "desc");
            payload.addProperty("SearchText", "");

            // Create the nested 'field' object
            JsonObject fieldObject = new JsonObject();
            JsonArray departmentIds = new JsonArray();
            departmentIds.add(1);
            departmentIds.add(2);

            // Set the department IDs correctly in the field object
            fieldObject.add("departement_id__in", departmentIds);
            payload.add("field", fieldObject); // Add the 'field' object to the main JSON object

            // Call the API
            Call<EmployeeAllFilterPageModel> call = NewApiClient.getInstance().getApiService(getActivity()).getEmployeeAllFilterPageList(payload);

            call.enqueue(new Callback<EmployeeAllFilterPageModel>() {
                @Override
                public void onResponse(Call<EmployeeAllFilterPageModel> call, Response<EmployeeAllFilterPageModel> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        if (response.body().getStatus() == 200) {
                            int n = response.body().getData().size();
                            ArrayList<String> salesEmpName = new ArrayList<>();

                            for (int i=0; i<n; i++) {
                                salesEmpName.add(response.body().getData().get(i).getSalesEmployeeName());
                                salesEmpParticipants.add(response.body().getData().get(i).getEmail());

                            }


                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, salesEmpName);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            binding.assignToSpinner.setAdapter(adapter);


                            binding.assignToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (!salesEmpParticipants.isEmpty()) {
                                        // Store the selected participant value in the variable
                                        selectedParticipantValue[0] = salesEmpParticipants.get(position);
                                        Log.d("Selected Participant", selectedParticipantValue[0]);
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    // Optional: handle if nothing is selected
                                }
                            });



                        } else {
                            Toast.makeText(requireContext(), "Api Failed", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<EmployeeAllFilterPageModel> call, Throwable t) {
                    Log.e("API Error", t.getMessage());
                    Toast.makeText(getActivity(), "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "An error occurred", Toast.LENGTH_SHORT).show();
        }

    }


}
