package com.example.majhashetkari.sellOption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.majhashetkari.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SellCrops extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_crops);


        getSupportActionBar().setTitle("Sell");

        getSupportFragmentManager().beginTransaction().replace(R.id.framelay, new sell_homefragment() ).commit();
       bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.getMenu().findItem(R.id.placeholder).setEnabled(false);
        floatingActionButton = findViewById(R.id.floatappbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment temp = null;
                switch (item.getItemId()){

                    case R.id.sellhome: temp=new sell_homefragment();
                    break;

                    case R.id.sellmyads: temp=new sell_myAddFragment();
                    break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framelay, temp).commit();
                return true;
            }

        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SellQuestions.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            }
        });




    }
}