package com.example.acer.uts2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    LowkerAdapter adapter;

    List<Lowker> lowkerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lowkerList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(i);
            }
        });

        lowkerList.add(
                new Lowker(
                        1,
                        "PT Manajemen Kawasan Gedung",
                        "Dicari Teknisi Support)",
                        "Jakarta Raya",
                        60000,
                        R.drawable.manajemenkawasangedung));

        lowkerList.add(
                new Lowker(
                        1,
                        "PT Exa Mitra Solusi",
                        "Dicari Information Technology UAT (IT UAT)",
                        "Jakarta Raya",
                        60000,
                        R.drawable.examitra));

        lowkerList.add(
                new Lowker(
                        1,
                        "PT Sarana Selectrode Indonesia",
                        "Dicari Sales Enginerr ESAB Welding",
                        "Cilegon Banten",
                        60000,
                        R.drawable.selectrode));

        lowkerList.add(
                new Lowker(
                        1,
                        "PT Intiland Development Tbk",
                        "Dicari Legal Officer",
                        "Bali, Bangka Belitung, Banten, Jakarta Raya, Jawa Barat",
                        60000,
                        R.drawable.intiland));

        lowkerList.add(
                new Lowker(
                        1,
                        "PT Kamoro Maxima Integra",
                        "Dicari IT Business Analyst - IT System Analyst",
                        "Jakarta Barat",
                        60000,
                        R.drawable.kamoro));

        lowkerList.add(
                new Lowker(
                        1,
                        "PT Aurora Group",
                        "Dicari Project Engineer",
                        "Makasar (Sulawesi Selatan), Manado (Sulawesi Utara)",
                        60000,
                        R.drawable.aurora));

        lowkerList.add(
                new Lowker(
                        1,
                        "PT Humana International Indonesia",
                        "Dicari Account Officer-Telecomunication",
                        "Medan Sumatra Utara",
                        60000,
                        R.drawable.humana));

        lowkerList.add(
                new Lowker(
                        1,
                        "CV Metal Prima Mandiri (MPM)",
                        "Dicari Staff Accounting",
                        "Surabaya, Jawa Timur",
                        60000,
                        R.drawable.metal));

        lowkerList.add(
                new Lowker(
                        1,
                        "Brilliant Internasional",
                        "Dicari Guru Kimia (Tentor)",
                        "Balikpapan, Kalimantan Timur",
                        60000,
                        R.drawable.brilliant));

        lowkerList.add(
                new Lowker(
                        1,
                        "CV Makmur Lestari",
                        "Dicari Staff Perpajakan",
                        "Kawasan Industri Candi Gatot Subroto Semarang, Jawa Tengah",
                        60000,
                        R.drawable.makmur));



        //creating recyclerview adapter
        adapter = new LowkerAdapter(this, lowkerList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Lowker lowker = lowkerList.get(position);
                Intent i =  new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("id", lowker.getId());
                i.putExtra("title", lowker.getTitle());
                i.putExtra("shortdesc", lowker.getShortdesc());
                i.putExtra("address", lowker.getAddress());
                i.putExtra("gaji", lowker.getGaji());
                i.putExtra("image", lowker.getImage());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void onClick(View view, int position) {
        Lowker data = lowkerList.get(position);
        Toast.makeText(getApplicationContext(), data.getTitle() + " is selected;", Toast.LENGTH_SHORT).show();
        Intent i =  new Intent(getApplicationContext(), DetailActivity.class);
        i.putExtra("id", data.getId());
        i.putExtra("title", data.getTitle());
        i.putExtra("shortdesc", data.getShortdesc());
        i.putExtra("address", data.getAddress());
        i.putExtra("gaji", data.getGaji());
        i.putExtra("image", data.getImage());
        startActivity(i);
    }
}
