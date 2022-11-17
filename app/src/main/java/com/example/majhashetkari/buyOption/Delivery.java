package com.example.majhashetkari.buyOption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majhashetkari.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Delivery extends AppCompatActivity implements PaymentResultListener {
    TextView edt1;
    Button paybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        edt1 = (TextView) findViewById(R.id.edt1);
        paybtn = (Button) findViewById(R.id.paybtn);

        String mamount = CartActivity.TotalAmount.getText().toString();
        edt1.setText(mamount);
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // amount that is entered by user.
                //String samount = CartActivity.TotalAmount.getText().toString(); /* edt1.getText().toString();*/
                //mamount = CartActivity.TotalAmount.getText().toString(); /* edt1.getText().toString(); */



                // rounding off the amount.
                int amount = Math.round(Float.parseFloat(mamount) * 100);

                // initialize Razorpay account.
                Checkout checkout = new Checkout();

                // set your id as below
                checkout.setKeyID("rzp_test_0zHAhqEqKMsAjO");

                // set image
                checkout.setImage(R.mipmap.ic_logo);

                // initialize json object
                JSONObject object = new JSONObject();
                try {
                    // to put name
                    object.put("name", "Maza Shetkari");

                    // put description
                    object.put("description", "Test payment");

                    // to set theme color
                    object.put("theme.color", "#3399cc");

                    // put the currency
                    object.put("currency", "INR");

                    // put amount
                    object.put("amount", amount);

                    // put mobile number
                    object.put("prefill.contact", "9869310932");

                    // put email
                    object.put("prefill.email", "atharvakashid1108@gmail.com");

                    // open razorpay to checkout activity
                    checkout.open(Delivery.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onPaymentSuccess(String s) {
        // this method is called on payment success.
        Toast.makeText(Delivery.this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        // on payment failed.
        Toast.makeText(Delivery.this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }
}