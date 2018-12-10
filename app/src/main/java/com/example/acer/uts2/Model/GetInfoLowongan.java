package com.example.acer.uts2.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetInfoLowongan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<InfoLowongan> listDataInfoLowongan;
    @SerializedName("message")
    String message;

    public void setStatus(String status) {

        this.status = status;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public List<InfoLowongan> getListDataInfoLowongan() {

        return listDataInfoLowongan;
    }

    public void setListDataInfoLowongan(List<InfoLowongan> listDataInfoLowongan) {
        this.listDataInfoLowongan = listDataInfoLowongan;
    }

}