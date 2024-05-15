package com.cinntra.vista.spinneradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cinntra.vista.R;
import com.cinntra.vista.model.DataBusinessType;
import com.cinntra.vista.model.DataDropDownZone;

import java.util.List;

public class ZoneSearchableSpinnerAdapter extends ArrayAdapter<String> {
    private final List<DataDropDownZone> dataList;
    private final LayoutInflater inflater;

    public ZoneSearchableSpinnerAdapter(Context context, List<DataDropDownZone> dataList) {
        super(context, R.layout.drop_down_textview);
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
        for (DataDropDownZone data : dataList) {
            add(data.getName()); // Replace with the actual method to get the first key
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Customize the appearance of the selected item in the dropdown (optional)
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.drop_down_textview, null);
        }
        TextView title = v.findViewById(R.id.title);
        title.setText(dataList.get(position).getName());
        return v;
    }
    // ... Other methods remain the same
}
