package com.cinntra.vista.model;

import java.util.ArrayList;

public class EmployeeAllFilterPageModel {
    public String message;
    public int status;
    public ArrayList<EmployeeAllFilterPageData> data;
    public Meta meta;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<EmployeeAllFilterPageData> getData() {
        return data;
    }

    public void setData(ArrayList<EmployeeAllFilterPageData> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}


