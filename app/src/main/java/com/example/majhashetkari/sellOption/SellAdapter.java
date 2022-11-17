package com.example.majhashetkari.sellOption;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.majhashetkari.R;

import java.util.List;


public class SellAdapter extends RecyclerView.Adapter<SellAdapter.ViewHolder> {

    private Context context;
    private List<SellModel> uploads;

    public SellAdapter(Context context, List<SellModel> uploads) {
        this.uploads = uploads;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sell_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SellModel upload = uploads.get(position);

        holder.textViewName.setText(upload.getName());

        Glide.with(context)
                .load(upload.getUrl())
                .into(holder.imageView);

        holder.textViewPrice.setText("₹" + upload.getPrice());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SellDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", upload.getName());
                intent.putExtra("price", upload.getPrice());
                intent.putExtra("image", upload.getUrl());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;

        public ImageView imageView;
        public TextView textViewPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewPrice =  (TextView) itemView.findViewById(R.id.textViewPrice);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}

