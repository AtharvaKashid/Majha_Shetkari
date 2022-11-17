package com.example.majhashetkari.buyOption;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.majhashetkari.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    ArrayList<ProductsModel> list;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        String type = getIntent().getStringExtra("type");

        firestore =  FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        list = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this, list);
        recyclerView.setAdapter(showAllAdapter);

        //Show products according to category
         /* if (type == null && type.isEmpty()) {
            firestore.collection("Products")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ProductsModel productsModel = doc.toObject(ProductsModel.class);
                                    list.add(productsModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        } */

        if (type != null && type.equalsIgnoreCase("tools")) {
            firestore.collection("Products").whereEqualTo("type", "tools")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ProductsModel productsModel = doc.toObject(ProductsModel.class);
                                    list.add(productsModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }

        if (type != null && type.equalsIgnoreCase("equipment")) {
            firestore.collection("Products").whereEqualTo("type", "equipment")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ProductsModel productsModel = doc.toObject(ProductsModel.class);
                                    list.add(productsModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }

        if (type != null && type.equalsIgnoreCase("fertilizers")) {
            firestore.collection("Products").whereEqualTo("type", "fertilizers")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ProductsModel productsModel = doc.toObject(ProductsModel.class);
                                    list.add(productsModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }

        if (type != null && type.equalsIgnoreCase("pesticides")) {
            firestore.collection("Products").whereEqualTo("type", "pesticides")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ProductsModel productsModel = doc.toObject(ProductsModel.class);
                                    list.add(productsModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }

        if (type != null && type.equalsIgnoreCase("others")) {
            firestore.collection("Products").whereEqualTo("type", "others")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ProductsModel productsModel = doc.toObject(ProductsModel.class);
                                    list.add(productsModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }

        if (type != null && type.equalsIgnoreCase("seeds")) {
            firestore.collection("Products").whereEqualTo("type", "seeds")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ProductsModel productsModel = doc.toObject(ProductsModel.class);
                                    list.add(productsModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }

        //Shows all product List
        /* firestore.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {

                                ProductsModel productsModel = doc.toObject(ProductsModel.class);
                                list.add(productsModel);
                                showAllAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                }); */
    }
}