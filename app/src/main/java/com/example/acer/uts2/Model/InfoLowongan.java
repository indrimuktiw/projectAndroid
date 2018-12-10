package com.example.acer.uts2.Model;

import com.google.gson.annotations.SerializedName;

public class InfoLowongan {
    @SerializedName("id_info_lowongan")
    private String idInfoLowongan;
    @SerializedName("id_perusahaan")
    private String idPerusahaan;
    @SerializedName("nama_pekerjaan")
    private String namaPekerjaan;
    @SerializedName("syarat")
    private String syarat;
    @SerializedName("id_kategori")
    private String idKategori;


    public InfoLowongan(String idInfoLowongan, String idPerusahaan, String namaPekerjaan, String syarat, String idKategori) {
        this.idInfoLowongan = idInfoLowongan;
        this.idPerusahaan = idPerusahaan;
        this.namaPekerjaan = namaPekerjaan;
        this.syarat = syarat;
        this.idKategori = idKategori;
    }

    public String getIdInfoLowongan() {
        return idInfoLowongan;
    }

    public void setIdInfoLowongan(String idInfoLowongan) {
        this.idInfoLowongan = idInfoLowongan;
    }

    public String getIdPerusahaan() {
        return idPerusahaan;
    }

    public void setIdPerusahaan(String idPerusahaan) {
        this.idPerusahaan = idPerusahaan;
    }

    public String getNamaPekerjaan() {
        return namaPekerjaan;
    }

    public void setNamaPekerjaan(String namaPekerjaan) {
        this.namaPekerjaan = namaPekerjaan;
    }

    public String getSyarat() {
        return syarat;
    }

    public void setSyarat(String syarat) {
        this.syarat = syarat;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }
}
