package com.example.majhashetkari.buyOption;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.majhashetkari.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    ArrayList<CartModel> list;
    double totalAmount = 0.0;
    FirebaseFirestore firestore;
    FirebaseAuth fAuth;

    public CartAdapter(Context context, ArrayList<CartModel> list) {
        this.context = context;
        this.list = list;

        firestore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getUrl()).into(holder.productImage);
        holder.name.setText(list.get(position).getProductName());
        holder.totalPrice.setText("Price: Rs " + list.get(position).getTotalPrice());
        holder.quantity.setText("Quantity: " + list.get(position).getTotalQuantity());


        holder.remove.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 firestore.collection("AddToCart").document("random")
                                                         .collection("User")
                                                         .document(list.get(position).getDocumentId())
                                                         .delete()
                                                         .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                             @Override
                                                             public void onComplete(@NonNull Task<Void> task) {
                                                                 if(task.isSuccessful())
                                                                 {
                                                                     list.remove(list.get(position));
                                                                     notifyDataSetChanged();
                                                                     Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();


                                                                 }
                                                                 else {
                                                                     Toast.makeText(context, "Error!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                 }
                                                             }
                                                         });
                                             }
                                         }
        );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView name, quantity, price, totalPrice;
        Button remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.p_image);
            name = itemView.findViewById(R.id.p_name);
            quantity = itemView.findViewById(R.id.p_quantity);
            totalPrice = itemView.findViewById(R.id.p_price);
            remove = itemView.findViewById(R.id.removeCartItem);



        }

        }
    }