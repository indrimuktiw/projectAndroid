package com.example.acer.uts2.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.acer.uts2.LayarDetail;
import com.example.acer.uts2.Model.InfoLowongan;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<InfoLowongan> mInfoLowonganList;

    public MyAdapter(List<InfoLowongan> infoLowonganList) {
        mInfoLowonganList = infoLowonganList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTextViewIdInfoLowongan.setText("Id Info Lowongan :  " + mInfoLowonganList.get(position)
                .getIdInfoLowongan());
        holder.mTextViewIdPerusahaan.setText("Id Perusahaan :  " + mInfoLowonganList.get(position)
                .getIdPerusahaan());
        holder.mTextViewIdKategori.setText("Id Kategori :  " + mInfoLowonganList.get(position).getIdKategori
                ());
        holder.mTextViewNamaPekerjaan.setText("Nama Pekerjaan :  " + mInfoLowonganList.get(position)
                .getNamaPekerjaan());
        holder.mTextViewSyarat.setText("Syarat :  " + String.valueOf(mInfoLowonganList.get
                (position).getSyarat()));
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mIntent = new Intent(view.getContext(), LayarDetail.class);
                mIntent.putExtra("id_info_lowongan",mInfoLowonganList.get(position).getIdInfoLowongan());
                mIntent.putExtra("id_perusahaan",mInfoLowonganList.get(position).getIdPerusahaan());
                mIntent.putExtra("nama_pekerjaan",mInfoLowonganList.get(position).getNamaPekerjaan());
                mIntent.putExtra("id_kategori",mInfoLowonganList.get(position).getIdKategori());
                mIntent.putExtra("syarat",String.valueOf(mInfoLowonganList.get(position).getSyarat()));
                view.getContext().startActivity(mIntent);
            }

        });
    }


    @Override
    public int getItemCount() {
        return mInfoLowonganList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewIdInfoLowongan, mTextViewIdPerusahaan, mTextViewNamaPekerjaan,mTextViewIdKategori,mTextViewSyarat;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewIdInfoLowongan = (TextView) itemView.findViewById(R.id.tvIdInfoLowongan);
            mTextViewIdPerusahaan = (TextView) itemView.findViewById(R.id.tvIdPerusahaan);
            mTextViewNamaPekerjaan = (TextView) itemView.findViewById(R.id.tvNamaPekerjaan);
            mTextViewIdKategori = (TextView) itemView.findViewById(R.id.tvIdKategori);
            mTextViewSyarat = (TextView) itemView.findViewById(R.id.tvSyarat);
        }
    }
}
