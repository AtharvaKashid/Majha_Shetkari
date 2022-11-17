package com.example.majhashetkari.DailyRates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.majhashetkari.R;

import java.util.List;

public class Dadapter extends RecyclerView.Adapter<Dadapter.ViewHolder>{

    private List<DLModel> modelList;

    public Dadapter(List<DLModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Dadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.dailyrates_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Dadapter.ViewHolder holder, int position) {

        holder.grainsname.setText(modelList.get(position).getName());
        holder.grainsrate.setText(modelList.get(position).getRate());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView grainsname;
        TextView grainsrate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            grainsname = itemView.findViewById(R.id.grainsname);
            grainsrate = itemView.findViewById(R.id.grainsrate);
        }
    }
}