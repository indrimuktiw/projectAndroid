package com.example.acer.uts2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
   TextView id, title, shortdesc, address, gaji;
   ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id=(TextView) findViewById(R.id.id);
//        title=(TextView) findViewById(R.id.textViewTitle);
        shortdesc=(TextView) findViewById(R.id.image_desc);
//        address=(TextView) findViewById(R.id.textViewAddress);
//        gaji=(TextView) findViewById(R.id.textViewGaji);
        image=(ImageView) findViewById(R.id.image);

        Intent i = getIntent();
        String idn=i.getStringExtra("id");
        String titlen=i.getStringExtra("title");
        String shortdescn=i.getStringExtra("shortdesc");
        String addressn=i.getStringExtra("address");
        int gajin=i.getIntExtra("gaji", 0);
        int thumbnail = i.getIntExtra("image", 0);

        id.setText(idn);
//        title.setText(titlen);
        shortdesc.setText(shortdescn);
//        address.setText(addressn);
//        gaji.setText(gajin);
        image.setImageResource(thumbnail);
    }
}
