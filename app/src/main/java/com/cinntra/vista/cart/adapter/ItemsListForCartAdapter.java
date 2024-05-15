package com.cinntra.vista.cart.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.cinntra.vista.R;
import com.cinntra.vista.globals.Globals;
import com.cinntra.vista.model.DocumentLines;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class ItemsListForCartAdapter extends RecyclerView.Adapter<ItemsListForCartAdapter.ItemListCrtViewHolder> {
    private ArrayList<DocumentLines> AllitemsList;
    private static final String TAG="ItemsListForCartAdapter";
    List<DocumentLines> tempList;
    private String SelectedTaxSlab = "";
    private int currentItemPo = 0;
    private int counter = 0;

    public ItemsListForCartAdapter(ArrayList<DocumentLines> AllitemsList) {
        this.AllitemsList = AllitemsList;
        this.tempList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemListCrtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_for_item_selection, parent, false);
        return new ItemListCrtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListCrtViewHolder holder, int position) {
        DocumentLines obj = getItem(position);

        holder.tvItemName.setText(obj.getItemName());
        holder.tvItemCode.setText(obj.getItemCode());

        if (obj.getUnitPrice() == 0) {
            holder.weight.setText(Prefs.getString(Globals.CURRENCY_AT_LOGIN,"") + " 0");
        } else {
            holder.weight.setText(Prefs.getString(Globals.CURRENCY_AT_LOGIN,"") + " " + String.valueOf(obj.getUnitPrice()));
        }

        holder.itemView.setOnClickListener(v -> {
            currentItemPo = holder.getAdapterPosition();
            DocumentLines itemObj = AllitemsList.get(currentItemPo);
            itemObj.setUnitPrice(AllitemsList.get(currentItemPo).getUnitPrice());
        });

        holder.bind(position, obj);
    }


    public void AllData(List<DocumentLines> tmp) {
        tempList.clear();
        tempList.addAll(tmp);
        notifyDataSetChanged();
    }

    public void filter(String newText) {
        String charText = newText.toLowerCase(Locale.getDefault()).trim();
        AllitemsList.clear();
        if (charText.length() == 0) {
            AllitemsList.addAll(tempList);
        } else {

            for (DocumentLines st : tempList) {
                /*if(st.getOpportunityName()!=null&&!st.getOpportunityName().isEmpty()) {
                    if (st.getCustomerName().toLowerCase().trim().contains(charText)) {*/
                if (st.getItemCode() != null && !st.getItemCode().isEmpty() && st.getItemName() != null && !st.getItemName().isEmpty()) {
                    if (st.getItemCode().toLowerCase().trim().contains(charText) || st.getItemName().toLowerCase().trim().contains(charText)) {
                        AllitemsList.add(st);
                    }
                }
            }

        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return AllitemsList.size();
    }

    public DocumentLines getItem(int position) {
        return AllitemsList.get(position);
    }

    public class ItemListCrtViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemName;
        private TextView tvItemCode;
        private TextView add,minus,plus;
        private TextView weight;
        private LinearLayout addNewItem;
        private LinearLayout addQuantity;
        private TextView total;

        public ItemListCrtViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemCode = itemView.findViewById(R.id.tvItemCode);
            weight = itemView.findViewById(R.id.weight);
            addNewItem = itemView.findViewById(R.id.add_new_item);
            addQuantity = itemView.findViewById(R.id.add_quantity);
            total = itemView.findViewById(R.id.total);
            add = itemView.findViewById(R.id.add);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
        }

        public void bind(int position, DocumentLines currentDocLine) {
            if (!Globals.SelectedItems.isEmpty()) {
                if (setupLocalArrayList(currentDocLine)) {
                    addNewItem.setVisibility(View.INVISIBLE);
                    addQuantity.setVisibility(View.VISIBLE);
                    for (DocumentLines currentInItem : Globals.SelectedItems) {
                        if (currentInItem.getItemCode().equalsIgnoreCase(currentDocLine.getItemCode())) {
                            total.setText(String.valueOf(currentInItem.getQuantity()));
                        }
                    }
                } else {
                    addNewItem.setVisibility(View.VISIBLE);
                    addQuantity.setVisibility(View.INVISIBLE);
                }
            }

            add.setOnClickListener(v -> {
                Globals.SelectedItems.add(currentDocLine);
                total.setText(String.valueOf(currentDocLine.getQuantity()));
                addQuantity.setVisibility(View.VISIBLE);
                addNewItem.setVisibility(View.INVISIBLE);
                notifyDataSetChanged();
            });

            // Other button click listeners (minus, plus) should be added similarly


            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = -1;
                    for (int index = 0; index < Globals.SelectedItems.size(); index++) {
                        DocumentLines documentLine = Globals.SelectedItems.get(index);
                        if (currentDocLine.getItemCode().equals(documentLine.getItemCode())) {
                            pos = index;
                            if (Integer.valueOf(Globals.SelectedItems.get(pos).getQuantity()) > 1) {
                                Globals.SelectedItems.get(pos).setQuantity(String.valueOf(Integer.valueOf(
                                        Globals.SelectedItems.get(pos).getQuantity()) - 1));
                                total.setText(String.valueOf(Globals.SelectedItems.get(pos).getQuantity()));
                                addNewItem.setVisibility(View.INVISIBLE);
                                addQuantity.setVisibility(View.VISIBLE);
                                notifyDataSetChanged();
                            } else {
                                Globals.SelectedItems.remove(pos);
                                addNewItem.setVisibility(View.VISIBLE);
                                addQuantity.setVisibility(View.INVISIBLE);
                                Log.e(TAG, "onBindViewHolder: MINUS CART - " + Globals.SelectedItems.toString());
                                Log.e(TAG, "onBindViewHolder: MINUS CART SIZE- " + Globals.SelectedItems.size());
                                notifyDataSetChanged();
                            }
                            return;
                        }
                    }
                }
            });

           plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = -1;
                    for (int index = 0; index < Globals.SelectedItems.size(); index++) {
                        DocumentLines documentLine = Globals.SelectedItems.get(index);
                        if (AllitemsList.get(position).getItemCode().equals(documentLine.getItemCode())) {
                            pos = index;
                            if (Integer.valueOf(Globals.SelectedItems.get(pos).getQuantity()) < 20) {
                                Globals.SelectedItems.get(pos).setQuantity(String.valueOf(Integer.valueOf(
                                        Globals.SelectedItems.get(pos).getQuantity() )+ 1));
                                total.setText(String.valueOf(Globals.SelectedItems.get(pos).getQuantity()));
                            }
                            return;
                        }
                    }
                }
            });



        }
    }

    private boolean setupLocalArrayList(DocumentLines currentItem) {
        for (DocumentLines item : Globals.SelectedItems) {
            if (item.getItemCode().equalsIgnoreCase(currentItem.getItemCode())) {
                return true;
            }
        }
        return false;
    }
}
