package com.example.acer.uts2.Model;

import com.google.gson.annotations.SerializedName;

public class Perusahaan {
    @SerializedName("id_perusahaan")
    private String idPerusahaan;
    @SerializedName("nama_perusahaan")
    private String namaPerusahaan;
    @SerializedName("alamatPerusahaan")
    private String alamatPerusahaan;
    @SerializedName("noTelpPerusahaan")
    private String noTelpPerusahaan;
    @SerializedName("photo_url")
    private String photoUrl;
    private String action;

    public Perusahaan(String idPerusahaan, String namaPerusahaan, String alamatPerusahaan, String noTelpPerusahaan, String photoUrl, String
            action) {
        this.idPerusahaan = idPerusahaan;
        this.namaPerusahaan = namaPerusahaan;
        this.alamatPerusahaan = alamatPerusahaan;
        this.noTelpPerusahaan = noTelpPerusahaan;
        this.photoUrl = photoUrl;
        this.action = action;
    }

    public String getIdPerusahaan() {
        return idPerusahaan;
    }

    public void setIdPerusahaan(String idPerusahaan) {
        this.idPerusahaan = idPerusahaan;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) { this.namaPerusahaan = namaPerusahaan;
    }

    public String getAlamatPerusahaan() {
        return alamatPerusahaan;
    }

    public void setAlamatPerusahaan(String alamatPerusahaan) {
        this.alamatPerusahaan = alamatPerusahaan;
    }

    public String getNoTelpPerusahaan() {
        return noTelpPerusahaan;
    }

    public void setNoTelpPerusahaan(String noTelpPerusahaantelp) {
        this.noTelpPerusahaan = noTelpPerusahaan;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
