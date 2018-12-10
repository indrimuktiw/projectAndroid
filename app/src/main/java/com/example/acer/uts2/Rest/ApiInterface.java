package com.example.acer.uts2.Rest;

import com.example.acer.uts2.Model.GetInfoLowongan;
import com.example.acer.uts2.Model.GetPerusahaan;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET("infoLowongan/user")
    Call<GetInfoLowongan> getInfoLowongan();




    /********* PERUSAHAAN *********/
    @GET("perusahaan/all")
    Call<GetPerusahaan> getPerusahaan();

    @Multipart
    @POST("perusahaan/all")
    Call<GetPerusahaan> postPerusahaan(
            @Part MultipartBody.Part file,
            @Part("nama_perusahaan") RequestBody nama_perusahaan,
            @Part("alamat_perusahaan") RequestBody alamat_perusahaan,
            @Part("no_telp_perusahaan") RequestBody no_telp_perusahaan,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("perusahaan/all")
    Call<GetPerusahaan> putPerusahaan(
            @Part MultipartBody.Part file,
            @Part("id_perusahaan") RequestBody idPerusahaan,
            @Part("nama_perusahaan") RequestBody nama_perusahaan,
            @Part("alamat_perusahaan") RequestBody alamat_perusahaan,
            @Part("no_telp_perusahaan") RequestBody no_telp_perusahaan,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("perusahaan/all")
    Call<GetPerusahaan> deletePerusahaan(
            @Part("id_perusahaan") RequestBody idPerusahaan,
            @Part("action") RequestBody action);


    //login
    @Multipart
    @POST("Rest_user/login")
    Call<GetLogin> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );
}
