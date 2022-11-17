package com.example.majhashetkari.buyOption;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.majhashetkari.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class  upiOrCashActivity extends AppCompatActivity {

    FirebaseFirestore db;

    EditText edt1, edt2, edt3;
    Button cashPay;
    ImageView razorPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upi_or_cash);

        db = FirebaseFirestore.getInstance();

        edt1 = findViewById(R.id.p_d_name);
        edt2 = findViewById(R.id.p_d_number);
        edt3 = findViewById(R.id.p_d_address);
        cashPay = findViewById(R.id.cashPay);
        razorPay = findViewById(R.id.razorPay);

        edt1.addTextChangedListener(textWatcher);
        edt2.addTextChangedListener(textWatcher);
        edt3.addTextChangedListener(textWatcher);
        cashPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = edt1.getText().toString();
                String Mobile = edt2.getText().toString();
                String Address = edt3.getText().toString();
                String Confirmation = "Order Placed (PaD)";
                if (Name.isEmpty() || Mobile.isEmpty() || Address.isEmpty()) {
                    Toast.makeText(upiOrCashActivity.this, "Name, Mobile number and Address is necessary", Toast.LENGTH_SHORT).show();
                } else if (Mobile.length() > 10 || Mobile.length() < 10) {
                    Toast.makeText(upiOrCashActivity.this, "Length of mobile number invalid", Toast.LENGTH_SHORT).show();
                }
                else {
                    HashMap<String, Object> h = new HashMap<>();
                    h.put("Name", Name);
                    h.put("Number", Mobile);
                    h.put("Address", Address);
                    h.put("Result", Confirmation);
                    db.collection("Cash on Delivery")
                            //.document("User")
                            .add(h)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference DocumentReference) {
                                    Toast.makeText(upiOrCashActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(upiOrCashActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Toast.makeText(Delivery.this, "Order Placed Sucessfully", Toast.LENGTH_SHORT).show();
                }
            }

        });

        razorPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = edt1.getText().toString();
                String Mobile = edt2.getText().toString();
                String Address = edt3.getText().toString();
                if (Name.isEmpty() || Mobile.isEmpty() || Address.isEmpty()) {
                    Toast.makeText(upiOrCashActivity.this, "Name, Mobile number and Address is necessary", Toast.LENGTH_SHORT).show();
                } else if (Mobile.length() > 10 || Mobile.length() < 10) {
                    Toast.makeText(upiOrCashActivity.this, "Length of mobile number invalid", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, Object> h = new HashMap<>();
                    h.put("Name", Name);
                    h.put("Number", Mobile);
                    h.put("Address", Address);
                    db.collection("Upi Payment")
                            //.document("User")
                            .add(h)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference DocumentReference) {
                                    Toast.makeText(upiOrCashActivity.this, "Details stored successfully!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(upiOrCashActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    Intent intent = new Intent(upiOrCashActivity.this,Delivery.class);
                    //intent.putExtra("amount", amount);
                    startActivity(intent);

                    //Toast.makeText(Delivery.this, "Order Placed Sucessfully", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String name = edt1.getText().toString();
            String number = edt2.getText().toString();
            String address = edt3.getText().toString();
            razorPay.setEnabled(!name.isEmpty() && !number.isEmpty() && !address.isEmpty());
            cashPay.setEnabled(!name.isEmpty() && !number.isEmpty() && !address.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}