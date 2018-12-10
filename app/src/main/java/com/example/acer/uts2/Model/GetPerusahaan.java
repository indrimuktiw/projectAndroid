package com.example.acer.uts2.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetPerusahaan {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Perusahaan> result = new ArrayList<Perusahaan>();
    @SerializedName("message")
    private String message;
    public GetPerusahaan() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Perusahaan> getResult() {
        return result;
    }

    public void setResult(List<Perusahaan> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}