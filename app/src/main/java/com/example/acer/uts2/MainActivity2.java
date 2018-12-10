package com.example.acer.uts2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.acer.uts2.Adapter.MyAdapter;
import com.example.acer.uts2.Model.GetInfoLowongan;
import com.example.acer.uts2.Model.InfoLowongan;
import com.example.acer.uts2.Rest.ApiClient;
import com.example.acer.uts2.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    Button btGet;
    ApiInterface mApiInterface ;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btGet = ( Button ) findViewById (R.id.btGet );

        mApiInterface = ApiClient.getClient ().create ( ApiInterface.class );

        mRecyclerView = (RecyclerView)  findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        btGet.setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Call<GetInfoLowongan> infoLowonganCall = mApiInterface.getInfoLowongan ();
                infoLowonganCall.enqueue ( new Callback<GetInfoLowongan>() {
                    @Override
                    public void onResponse (Call <GetInfoLowongan> call , Response<GetInfoLowongan>
                            response ) {
                        List<InfoLowongan> infoLowonganList = response.body ().getListDataInfoLowongan();
                        Log. d ( "Retrofit Get" , "Jumlah data info lowongan: " +
                                String . valueOf ( infoLowonganList . size ()));
                        mAdapter = new MyAdapter(infoLowonganList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    @Override
                    public void onFailure (Call <GetInfoLowongan> call , Throwable t ) {
// Log error
                        Log . e ( "Retrofit Get" , t . toString ());
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {


            case R.id.menuListPerusahaan:
                mIntent = new Intent(this, LayarListPerusahaan.class);
                startActivity(mIntent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
