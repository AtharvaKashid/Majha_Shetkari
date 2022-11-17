package com.example.majhashetkari.buyOption;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.majhashetkari.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Details extends AppCompatActivity {

    FirebaseFirestore db;

    EditText edtt1,edtt2,edtt3;
    Button btnn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //get data from detailed activity
        Object obj = getIntent().getSerializableExtra("item");

        db = FirebaseFirestore.getInstance();

        edtt1 = findViewById(R.id.p_d_name);
        edtt2 = findViewById(R.id.p_d_number);
        edtt3 = findViewById(R.id.p_d_address);
        btnn1 = findViewById(R.id.btnn1);

        btnn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = edtt1.getText().toString();
                String Number = edtt2.getText().toString().trim();
                String Address = edtt3.getText().toString();
                String getConfirmation = " Order Placed";
                if(Name.isEmpty() || Number.isEmpty() || Address.isEmpty()){
                    Toast.makeText(Details.this, "Name ,Number and Address is Necessary", Toast.LENGTH_SHORT).show();
                }else {
                    HashMap<String,Object> h = new HashMap<>();
                    h.put("Name",Name);
                    h.put("Number",Number);
                    h.put("Address",Address);
                    h.put("Result",getConfirmation);
                    FirebaseFirestore.getInstance().collection("User")
                            //.document("User-data")
                            .add(h)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference DocumentReference) {
                                    Toast.makeText(Details.this, "Data Stored Successfully", Toast.LENGTH_SHORT).show();
                                    clearText();
                                }

                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Details.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    int amount = 0;
                    if (obj instanceof ProductsModel) {
                        ProductsModel productsModel = (ProductsModel) obj;
                        amount = productsModel.getPrice();
                    }
                    Intent intent = new Intent(Details.this,MainClass.class);
                    intent.putExtra("amount", amount);
                    startActivity(intent);
                }
            }
        });


    }

    private void clearText() {
        edtt1.setText("");
        edtt2.setText("");
        edtt3.setText("");
    }
}