package com.example.majhashetkari.buyOption;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.majhashetkari.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView description, name, rating, price, quantity;
    Button addToCart, buyNow;
    ImageView addItems, removeItems;
    int totalQuantity = 1;
    int totalPrice = 0;

    ProductsModel productsModel = null;
    //ShowAllModel showAllModel = null;

    FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detailed);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof ProductsModel) {
            productsModel = (ProductsModel) obj;
        } //else if (obj instanceof ShowAllModel) {
            //showAllModel = (ShowAllModel) obj;
       // }

        detailedImg = findViewById(R.id.detailed_image);
        description = findViewById(R.id.detailed_description);
        name = findViewById(R.id.detailed_product_name);
        rating = findViewById(R.id.detailed_rating);
        price = findViewById(R.id.detailed_price);
        quantity = findViewById(R.id.detailed_quantity);

        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);

        addItems = findViewById(R.id.add_item);
        removeItems = findViewById(R.id.remove_item);

        //Home Products, i.e. show the product details
        if(productsModel != null) {
            Glide.with(getApplicationContext()).load(productsModel.getImage_url()).into(detailedImg);
            name.setText(productsModel.getName());
            rating.setText("Rating: " + productsModel.getRating());
            description.setText(productsModel.getDescription());
            price.setText("Rs " + productsModel.getPrice());

            totalPrice = productsModel.getPrice() * totalQuantity;
            //price.setText("Rs " + totalPrice);
        }

        //Show all model, i.e., show details of products from Show All Activity
      /*  if(showAllModel != null) {
            Glide.with(getApplicationContext()).load(showAllModel.getImage_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));
            name.setText(showAllModel.getName());

            //totalPrice = productsModel.getPrice() * totalQuantity;
        } */

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtoCart();
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailedActivity.this, upiOrCashActivity.class);
                /*if (productsModel != null) {
                    intent.putExtra("item", productsModel);
                }*/
                startActivity(intent);
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                    if (productsModel != null) {
                        totalPrice = productsModel.getPrice() * totalQuantity;
                    }
                }
            }
        });

        removeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

    }



    private void addtoCart() {

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productName", name.getText().toString());
       // cartMap.put("productImage",detailedImg.getAutofillValue().toString();
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.collection("AddToCart"). document("random")
                .collection("User") /*document(auth.getCurrentUser().getUid())
                .collection("User").*/ .add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this, "Successfully Added to Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}