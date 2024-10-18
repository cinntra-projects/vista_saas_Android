package com.cinntra.vista.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class QuotationStringResponse implements Serializable {
    @SerializedName("data")
    ArrayList<QuotationStringItem> value;
    @SerializedName("error")
    Error error;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public ArrayList<QuotationStringItem> getValue()
      {
    return value;
      }

    public void setValue(ArrayList<QuotationStringItem> value)
      {
    this.value = value;
      }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
