package com.example.majhashetkari.buyOption;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.majhashetkari.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    CartAdapter cartAdapter;
    ArrayList<CartModel> list;
    RecyclerView recyclerView;
    public static TextView TotalAmount;
    Button proceedUpi;
    SwipeRefreshLayout swipeRefreshLayout;
    ImageView emptycrtimg;
    TextView empytycrt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        FirebaseAuth auth = FirebaseAuth.getInstance();
    //    FirebaseFirestore firestore = FirebaseFirestore.getInstance();


        proceedUpi = findViewById(R.id.proceedUpi);
        TotalAmount = findViewById(R.id.total_amount);
        recyclerView = findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        cartAdapter = new CartAdapter(this, list);
        recyclerView.setAdapter(cartAdapter);
        Button CartRemove = findViewById(R.id.removeCartItem);
        empytycrt = findViewById(R.id.emptycartxt);
        emptycrtimg = findViewById(R.id.emptycarimg);



        cartdata();

        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cartdata();
                cartAdapter.notifyDataSetChanged();
                list.clear();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(com.mancj.materialsearchbar.R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);



        proceedUpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, upiOrCashActivity.class);
                startActivity(intent);
            }
        });





        /* firestore.collection("AddToCart").document("random")
                 .collection("User")./* document(auth.getCurrentUser().getUid())
                .collection("User"). */ /* get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        CartModel cartModel = doc.toObject(CartModel.class);
                        list.add(cartModel);
                    }
                    cartAdapter.notifyDataSetChanged();
                }
            }
        }); */

    }

    private void CalculateTotalAmount(ArrayList<CartModel> list) {

         double totalamount = 0.0;
         for(CartModel cartModel : list)
         {
             totalamount += cartModel.getTotalPrice();
         }

         TotalAmount.setText(""+totalamount);
    }


    private void cartdata(){

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("AddToCart").document("random")
                .collection("User")./* document(auth.getCurrentUser().getUid())
                .collection("User"). */ get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d:list1) {
                            String documentId = d.getId();

                            CartModel obj = d.toObject(CartModel.class);

                            obj.setDocumentId(documentId);

                            list.add(obj);

                        }
                        CalculateTotalAmount(list);
                        cartAdapter.notifyDataSetChanged();
                    }
                });
    }
}