package com.cinntra.vista.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.cinntra.vista.R;
import com.cinntra.vista.fragments.UpdateActivityEventDetailFragment;
import com.cinntra.vista.globals.Globals;
import com.cinntra.vista.model.EventResponse;
import com.cinntra.vista.model.EventValue;
import com.cinntra.vista.model.QuotationResponse;
import com.cinntra.vista.webservices.NewApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityEventsAdapter extends RecyclerView.Adapter<ActivityEventsAdapter.EventViewHolder> {
    Context context;


    ArrayList<EventValue> allEventTaskList;

    public ActivityEventsAdapter(Context context, ArrayList<EventValue> allEventTaskList) {
        this.context = context;
        this.allEventTaskList = allEventTaskList;
        this.templist = new ArrayList<>();
        this.templist.addAll(allEventTaskList);

    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.activityevents_item, parent, false);
        return new EventViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {


        if (allEventTaskList.get(position).getTitle() != null && !allEventTaskList.get(position).getTitle().isEmpty()) {
            holder.title.setText("Event: "+allEventTaskList.get(position).getTitle());
        } else {
            holder.title.setText("Event: NA");
        }

        if (allEventTaskList.get(position).getDescription() != null && !allEventTaskList.get(position).getDescription().isEmpty()) {
            holder.location.setText(allEventTaskList.get(position).getDescription());
        } else {
            holder.location.setText("NA");
        }

        if (allEventTaskList.get(position).getTime() != null && !allEventTaskList.get(position).getTime().isEmpty()) {
            holder.timing.setText(allEventTaskList.get(position).getTime());
        } else {
            holder.timing.setText("NA");
        }
        if (allEventTaskList.get(position).getTo() != null && !allEventTaskList.get(position).getTo().isEmpty()) {
            holder.tvDateTime.setText(Globals.convert_yyyy_mm_dd_to_dd_mm_yyyy(allEventTaskList.get(position).getTo()));
        } else {
            holder.tvDateTime.setText("NA");
        }
        if (allEventTaskList.get(position).getToTime() != null && !allEventTaskList.get(position).getToTime().isEmpty()) {
            holder.tvToTime.setText(" at "+allEventTaskList.get(position).getToTime());
        } else {
            holder.tvToTime.setText(" at "+"NA");
        }
        if (allEventTaskList.get(position).getFrom() != null && !allEventTaskList.get(position).getFrom().isEmpty()) {
            holder.tvStartTime.setText(allEventTaskList.get(position).getFrom());
        } else {
            holder.tvStartTime.setText("NA");
        }
        if (allEventTaskList.get(position).getTime() != null && !allEventTaskList.get(position).getTime().isEmpty()) {
            holder.tvTime.setText(" at "+allEventTaskList.get(position).getTime());
        } else {
            holder.tvTime.setText(" at "+"NA");
        }

        holder.ivdeleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDeletApi(allEventTaskList.get(position).getId());
                allEventTaskList.remove(position);
                notifyDataSetChanged();
            }
        });


//        holder.tvLocation.setText("Location - " + allEventTaskList.get(position).getLocation());

//        holder.threeDotsLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showPopup(holder.threeDotsLayout, allEventTaskList, position);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return allEventTaskList.size();
    }


    ArrayList<EventValue> templist;

    public void filter(String text) {

        allEventTaskList.clear();

        for (EventValue st : templist) {

            if (st.getType().equals(text)) {

                allEventTaskList.add(st);

            }


        }
        notifyDataSetChanged();

    }


    class EventViewHolder extends RecyclerView.ViewHolder {

        LinearLayout eventView, threeDotsLayout;
        TextView title, location, timing, tvLocation,tvDateTime, tvToTime, tvStartTime, tvTime;
        ImageView priority_dot, ivdeleteEvent;

        EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventView = itemView.findViewById(R.id.eventView);
            title = itemView.findViewById(R.id.title);
            location = itemView.findViewById(R.id.location);
            timing = itemView.findViewById(R.id.timing);
            priority_dot = itemView.findViewById(R.id.priority_dot);
            threeDotsLayout = itemView.findViewById(R.id.threeDotsLayout);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvToTime = itemView.findViewById(R.id.tvToTime);
            ivdeleteEvent = itemView.findViewById(R.id.ivdeleteEvent);
            tvStartTime = itemView.findViewById(R.id.tvStartTime);
            tvTime = itemView.findViewById(R.id.tvTime);

//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//
//                    PopupMenu popup = new PopupMenu(v.getContext(), itemView);
//                    popup.getMenuInflater().inflate(R.menu.longpress_menu, popup.getMenu());
//                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item) {
//                            if (item.getItemId() == R.id.edit) {
//                                Bundle b = new Bundle();
//                                b.putParcelable("View", allEventTaskList.get(getAdapterPosition()));
//                                b.putInt("Position", getAdapterPosition());
//                                UpdateActivityEventDetailFragment fragment = new UpdateActivityEventDetailFragment();
//                                fragment.setArguments(b);
//                                FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
//                                transaction.replace(R.id.frame, fragment);
//                                transaction.addToBackStack("");
//                                transaction.commit();
//                            } else {
//
//                                callDeletApi(allEventTaskList.get(getAdapterPosition()).getId());
//                                allEventTaskList.remove(getAdapterPosition());
//                                notifyDataSetChanged();
//
//                            }
//                            return true;
//                        }
//                    });
//                    popup.show();
//                    return true;
//                }
//            });

        }
    }


    //todo show popup or edit and delete items---
    private void showPopup(View v, ArrayList<EventValue> eventValue, int position){
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.longpress_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.edit) {
                    Bundle b = new Bundle();
                    b.putParcelable("View", eventValue.get(position));
                    b.putInt("Position", position);
                    UpdateActivityEventDetailFragment fragment = new UpdateActivityEventDetailFragment();
                    fragment.setArguments(b);
                    FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, fragment);
                    transaction.addToBackStack("");
                    transaction.commit();
                } else {

                    callDeletApi(eventValue.get(position).getId());
                    eventValue.remove(position);
                    notifyDataSetChanged();

                }
                return true;
            }
        });
        popup.show();
    }

    private void callDeletApi(Integer id) {

        EventValue eventValue = new EventValue();
        eventValue.setId(id);

        Call<EventResponse> call = NewApiClient.getInstance().getApiService(context).deleteEvent(eventValue);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.body().getStatus() == 200) {
                    Toast.makeText(context, "Deleted Successfully.", Toast.LENGTH_SHORT).show();

                } else {
                    //Globals.ErrorMessage(CreateContact.this,response.errorBody().toString());
                    Gson gson = new GsonBuilder().create();
                    QuotationResponse mError = new QuotationResponse();
                    try {
                        String s = response.errorBody().string();
                        mError = gson.fromJson(s, QuotationResponse.class);
                        Toast.makeText(context, mError.getError().getMessage().getValue(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        //handle failure to read error
                    }
                    //Toast.makeText(CreateContact.this, msz, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}
