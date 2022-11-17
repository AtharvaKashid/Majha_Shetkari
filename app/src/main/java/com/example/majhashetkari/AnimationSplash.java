package com.example.majhashetkari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AnimationSplash extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = fAuth.getCurrentUser();
        final  Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firebaseUser == null){
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    finish();
                }
                else{
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        },3200);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_splash);

        fAuth = FirebaseAuth.getInstance();


    }
    }
