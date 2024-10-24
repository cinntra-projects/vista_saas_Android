package com.cinntra.vista.model;

import com.google.gson.annotations.SerializedName;

public class RoleDetails {
    public int id;
    @SerializedName("DepartmentName")
    public String departmentName;
    @SerializedName("Name")
    public String name;
    @SerializedName("Level")
    public String level;
    @SerializedName("Status")
    public String status;
    @SerializedName("Department")
    public int department;
}
