package com.example.acer.uts2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.acer.uts2.Adapter.PerusahaanAdapter;
import com.example.acer.uts2.Model.GetPerusahaan;
import com.example.acer.uts2.Model.Perusahaan;
import com.example.acer.uts2.Rest.ApiClient;
import com.example.acer.uts2.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListPerusahaan extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context mContext;
    ApiInterface mApiInterface;
    Button btAddData, btGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_perusahaan);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btAddData = (Button) findViewById(R.id.btAddData);
        btGet = (Button) findViewById(R.id.btGet);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetPerusahaan> mPerusahaanCall = mApiInterface.getPerusahaan();
                mPerusahaanCall.enqueue(new Callback<GetPerusahaan>() {
                    @Override
                    public void onResponse(Call<GetPerusahaan> call, Response<GetPerusahaan> response) {
                        Log.d("Get Perusahaan",response.body().getStatus());
                        List<Perusahaan> listPerusahaan = response.body().getResult();
                        mAdapter = new PerusahaanAdapter(listPerusahaan);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetPerusahaan> call, Throwable t) {
                        Log.d("Get Perusahaan",t.getMessage());
                    }
                });
            }
        });

    }



}

