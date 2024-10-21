package com.cinntra.vista.adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cinntra.vista.R;
import com.cinntra.vista.model.UTypeData;

import java.util.List;

public class TypeDropDownAdapter extends ArrayAdapter<UTypeData> {
    Context context;
    int resourceId;
    List<UTypeData> items;

    public TypeDropDownAdapter(@NonNull Context context, int resourceId, List<UTypeData> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(resourceId, parent, false);
        }
        UTypeData model = getItem(position);
        TextView name = view.findViewById(R.id.text_view);
        if (model != null) {

            name.setText(model.getType());
        }
        return view;
    }

    @Nullable
    @Override
    public UTypeData getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}


