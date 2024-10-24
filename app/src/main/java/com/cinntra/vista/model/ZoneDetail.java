package com.cinntra.vista.model;

import com.google.gson.annotations.SerializedName;

public class ZoneDetail {
    public int id;
    @SerializedName("Name")
    public String name;
    @SerializedName("Status")
    public String status;
}
