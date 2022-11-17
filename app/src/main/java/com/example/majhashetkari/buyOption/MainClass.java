package com.example.majhashetkari.buyOption;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.majhashetkari.R;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainClass extends AppCompatActivity {
    EditText edt1,edt2, /*edt3,*/ edt4,edt5,edt6;
    TextView subTotal;
    Button pay;

    final   int PAY_REQUEST = 1;
    String AES="AES";
    String upiId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class);

        int amount = 0;
        int Total = 0;
        amount = getIntent().getIntExtra("amount", 0);
        Total = getIntent().getIntExtra("totalBill", 0);

        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        subTotal =findViewById(R.id.edt3);
        edt4=findViewById(R.id.edt4);
        edt5=findViewById(R.id.edt5);
        edt6=findViewById(R.id.edt6);
        pay = findViewById(R.id.btn);

        subTotal.setText("Total amount: " + "Rs " + Total);

        DAO d = new DAO();

        pay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                try {
                    upiId=encrypt(edt2.getText().toString());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                Model m = new Model(edt1.getText().toString(),upiId, subTotal.getText().toString(),edt4.getText().toString(),edt5.getText().toString(),edt6.getText().toString());
                d.add(m).addOnSuccessListener(success->{
                    if(edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty() || subTotal.getText().toString().isEmpty()){
                        Toast.makeText(MainClass.this, "Name , UPI ID and Amount is Necessary", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainClass.this, "Record is stored Successfully", Toast.LENGTH_SHORT).show();

                    }
                    clearText();
                }).addOnFailureListener(error->{
                    Toast.makeText(MainClass.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                });
                String name = edt1.getText().toString();
                String upiID = edt2.getText().toString();
                String amt = subTotal.getText().toString();
                //String amt = CartActivity.
                String msg = edt4.getText().toString();
                String trnID = edt5.getText().toString();
                String refID = edt6.getText().toString();

                if(name.isEmpty() || upiID.isEmpty() || amt.isEmpty()){
                    Toast.makeText(MainClass.this, "Name UPI ID and Amount  is Necessary", Toast.LENGTH_SHORT).show();
                }else{
                    PayUsingUpi(name,upiID,amt,msg,trnID,refID);
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String encrypt(String upiId) throws Exception{
        SecretKeySpec key = generateKey(upiId);
        Cipher c=Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encVal=c.doFinal();
        String encryptedValue= Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;

    }

    private SecretKeySpec generateKey(String upiId)throws Exception {
        final MessageDigest digest=MessageDigest.getInstance("SHA-256");
        byte[] bytes = upiId.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");

        return secretKeySpec;
    }

    private void clearText() {
        edt1.setText("");
        edt2.setText("");
    }

    private void PayUsingUpi(String name,String upiId,String amt,String msg,String trnId,String refId)
    {
        Uri uri =new Uri.Builder()
                .scheme("upi").authority("pay")
                .appendQueryParameter("pa",upiId)
                .appendQueryParameter("pn",name)
                .appendQueryParameter("tn",msg)
                .appendQueryParameter("am",amt)
                .appendQueryParameter("tid",trnId)
                .appendQueryParameter("tr",refId)
                .appendQueryParameter("cu","INR")
                .build();
        Intent upiIntent = new Intent(Intent.ACTION_VIEW);
        upiIntent.setData(uri);
        Intent selector = Intent.createChooser(upiIntent,"Pay");
        if(selector.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(selector,PAY_REQUEST);
        }else{
            Toast.makeText(this, "No UPI app found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PAY_REQUEST)
        {
            if(isInternetAvailable(MainClass.this))
            {
                if ( data == null){
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    String temp = "nothing";
                    Toast.makeText(this, "Transaction not Complete", Toast.LENGTH_SHORT).show();
                }else{
                    String text = data.getStringExtra("response");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add(text);
                    paymentCheck(text);
                }
            }
        }
    }
    void paymentCheck(String data){
        String str = data;
        String payment_cancel = "";
        String status = "";
        String response[] = str.split("&");
        for (int i=0;i< response.length;i++){
            String equalStr[]=response[i].split("");
            if(equalStr.length >= 2)
            {
                if(equalStr[0].toLowerCase().equals("Status".toLowerCase())){
                    status = equalStr[1].toLowerCase();
                }
            }else{
                payment_cancel = "Payment Cancelled";
            }

            if(status.equals("success")){
                Toast.makeText(this, "Transaction Successfull", Toast.LENGTH_SHORT).show();
            }else if("Payment Cancelled".equals(payment_cancel)){
                Toast.makeText(this, "Payment Cancelled by User", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Transaction failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static boolean isInternetAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null)
        {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo.isConnected() && networkInfo.isConnectedOrConnecting() && networkInfo.isAvailable()){
                return true;
            }
        }
        return  false;
    }

}