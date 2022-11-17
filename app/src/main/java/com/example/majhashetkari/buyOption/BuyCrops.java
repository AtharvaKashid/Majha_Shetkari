package com.example.majhashetkari.buyOption;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.majhashetkari.R;

import java.util.ArrayList;

public class BuyCrops extends AppCompatActivity {

    Fragment homeFragment;
    ImageView shopping_cart;
    ShowAllAdapter showAllAdapter;
    ArrayList<ProductsModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_crops);

        homeFragment = new HomeFragment();
        loadFragment(homeFragment);

        shopping_cart = (ImageView) findViewById(R.id.cart_click);
        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyCrops.this, CartActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadFragment(Fragment homeFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, homeFragment);
        transaction.commit();
    }
}