package com.example.majhashetkari.buyOption;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.majhashetkari.R;

import java.util.ArrayList;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ViewHolder> {

    private Context context;
    ArrayList<ProductsModel> list;

    public ShowAllAdapter(Context context, ArrayList<ProductsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImage_url()).into(holder.productImage);
        holder.productName.setText(list.get(position).getName());
        holder.price.setText("Rs " + String.valueOf(list.get(position).getPrice()));
        holder.rating.setText("Rating: " +list.get(position).getRating());
        holder.status.setText(list.get(position).getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailedActivity.class);
                //intent.putExtra("detailed", list.get(position));
                intent.putExtra("detailed", list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName, price, rating, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.showAllImage);
            productName = itemView.findViewById(R.id.showAllName);
            price = itemView.findViewById(R.id.showAllPrice);
            rating = itemView.findViewById(R.id.showAllRating);
            status = itemView.findViewById(R.id.showAllStatus);
        }
    }
}
