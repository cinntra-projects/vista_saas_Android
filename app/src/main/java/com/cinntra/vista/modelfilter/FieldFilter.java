package com.cinntra.vista.modelfilter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FieldFilter {
    public ArrayList<String> assignedTo_id__in;
    public ArrayList<String> source__in;

    @SerializedName("CreateDate__gte")
    public String from_date;

    @SerializedName("CreateDate__lte")
    public String to_date;


    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }


    public FieldFilter() {

    }


    public FieldFilter(ArrayList<String> assignedTo_id__in, ArrayList<String> source__in) {
        this.assignedTo_id__in = assignedTo_id__in;
        this.source__in = source__in;
    }




    public ArrayList<String> getAssignedTo_id__in() {
        return assignedTo_id__in;
    }

    public void setAssignedTo_id__in(ArrayList<String> assignedTo_id__in) {
        this.assignedTo_id__in = assignedTo_id__in;
    }

    public ArrayList<String> getSource__in() {
        return source__in;
    }

    public void setSource__in(ArrayList<String> source__in) {
        this.source__in = source__in;
    }


}
