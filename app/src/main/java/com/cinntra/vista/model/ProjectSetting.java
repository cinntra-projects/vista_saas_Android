package com.cinntra.vista.model;

import com.google.gson.annotations.SerializedName;

public class ProjectSetting {
    public int id;
    public String client_id;
    public String currency_type;
    public String currency_value;
    public String default_country_code;
    public String default_country_name;
    public String default_time_zone;
    public String custom_field1;
    public String custom_field2;
    public String custom_field3;
    public String custom_field4;
    public String custom_field5;
    @SerializedName("CreateDate")
    public String createDate;
    @SerializedName("CreateTime")
    public String createTime;
    @SerializedName("UpdateDate")
    public String updateDate;
    @SerializedName("UpdateTime")
    public String updateTime;
}
