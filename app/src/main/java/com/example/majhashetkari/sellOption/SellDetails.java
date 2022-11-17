package com.example.majhashetkari.sellOption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.majhashetkari.R;
import com.example.majhashetkari.databinding.ActivitySellDetailsBinding;

public class SellDetails extends AppCompatActivity {

    ActivitySellDetailsBinding binding;
    TextView prName;
    TextView prDesc;
    TextView prPrice;
    String phone = "7620384235";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySellDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name = getIntent().getStringExtra("name");

        String image = getIntent().getStringExtra("image");

        double price = getIntent().getDoubleExtra("price", 0);

        String description = getIntent().getStringExtra("description");

        Glide.with(this)
                .load(image)
                .into(binding.productImage);

        prName = findViewById(R.id.prNameAC);

        prName.setText(name);

        prPrice = findViewById(R.id.prPrice);

        prPrice.setText("₹ "+price);

        prDesc = findViewById(R.id.prDesc);

        prDesc.setText(description);

    }

    public void onCall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String temp = "tel:" + phone;
        intent.setData(Uri.parse(temp));
        startActivity(intent);
    }

    public void onWhatsapp(View view) {
        PackageManager pm=getPackageManager();
        try {
            String text = "Hey! \nI saw your ad on Majha Shetkari App. I am interested in buying it. Is it still available for sale?";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=+91"+phone +"&text="+text));
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}

