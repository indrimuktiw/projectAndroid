package com.example.acer.uts2.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.uts2.Model.Perusahaan;

import java.util.List;

public class PerusahaanAdapter extends RecyclerView.Adapter<PerusahaanAdapter.PerusahaanViewHolder>{
    List<Perusahaan> listPerusahaan;

    public PerusahaanAdapter(List<Perusahaan> listPerusahaan){
        this.listPerusahaan = listPerusahaan;
    }

    @Override
    public PerusahaanAdapter.PerusahaanViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_perusahaan, parent, false);
        PerusahaanViewHolder mHolder = new PerusahaanViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(PerusahaanAdapter.PerusahaanViewHolder holder, final int position){
        holder.tvIdPerusahaan.setText(listPerusahaan.get(position).getIdPerusahaan());
        holder.tvNamaPerusahaan.setText(listPerusahaan.get(position).getNamaPerusahaan());
        holder.tvAlamatContentPrshn.setText(listPerusahaan.get(position).getAlamatPerusahaan());
        holder.tvNoTelpPerusahaan.setText(listPerusahaan.get(position).getNoTelpPerusahaan());
        if (listPerusahaan.get(position).getPhotoUrl() != null ){
            //Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listPerusahaan.get(position).getPhotoId()).into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(listPerusahaan.get(position).getPhotoUrl()).into(holder.mPhotoURL);
        } else {
            //Picassp.with(holder.itemView.getContext()).load(R.drawable.photoid).into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(R.drawable.default_user).into(holder.mPhotoURL);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), LayarEditPerusahaan.class);
                intent.putExtra("id_perusahaan", listPerusahaan.get(position).getIdPerusahaan());
                intent.putExtra("nama_perusahaan", listPerusahaan.get(position).getNamaPerusahaan());
                intent.putExtra("alamat_perusahaan", listPerusahaan.get(position).getAlamatPerusahaan());
                intent.putExtra("no_telp_perusahaan", listPerusahaan.get(position).getNoTelpPerusahaan());
                intent.putExtra("photo_url", listPerusahaan.get(position).getPhotoUrl());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return listPerusahaan.size();
    }

    public class PerusahaanViewHolder extends RecyclerView.ViewHolder{
        ImageView mPhotoURL;
        TextView tvIdPerusahaan, tvNamaPerusahaan, tvAlamatContentPrshn, tvNoTelpPerusahaan;

        public PerusahaanViewHolder(View itemView){
            super(itemView);
            mPhotoURL = (ImageView) itemView.findViewById(R.id.imgPerusahaan);
            tvIdPerusahaan = (TextView) itemView.findViewById(R.id.tvIdPerusahaan);
            tvNamaPerusahaan = (TextView) itemView.findViewById(R.id.tvNamaPerusahaan);
            tvAlamatContentPrshn = (TextView) itemView.findViewById(R.id.tvAlamatContentPrshn);
            tvNoTelpPerusahaan = (TextView) itemView.findViewById(R.id.tvNoTelpPerusahaan);
        }
    }
}
