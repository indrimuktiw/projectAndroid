package com.example.acer.uts2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LowkerAdapter extends RecyclerView.Adapter<LowkerAdapter.LowkerViewHolder>{
private Context mCtx;
private List<Lowker> lowkerList;

    public LowkerAdapter(Context mCtx, List<Lowker> lowkerList) {
        this.mCtx = mCtx;
        this.lowkerList = lowkerList;
    }


    @Override
    public LowkerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new LowkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LowkerViewHolder holder, int i) {
        Lowker lowker = lowkerList.get(i);

        holder.textViewTitle.setText(String.valueOf(lowker.getTitle()));
        holder.textViewShortDesc.setText(String.valueOf(lowker.getShortdesc()));
        holder.textViewAddress.setText(String.valueOf(lowker.getAddress()));
        holder.textViewGaji.setText(String.valueOf(lowker.getGaji()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(lowker.getImage()));
    }

    @Override
    public int getItemCount() {
        return lowkerList.size();


    }



    public class LowkerViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle, textViewShortDesc, textViewAddress, textViewGaji;

        public LowkerViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            textViewGaji = itemView.findViewById(R.id.textViewGaji);
        }
    }
}
