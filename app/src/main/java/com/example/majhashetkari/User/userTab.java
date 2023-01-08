package com.example.majhashetkari.User;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.majhashetkari.Login;
import com.example.majhashetkari.MainActivity;
import com.example.majhashetkari.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userTab extends Fragment {

     FirebaseAuth mAuth;
     TextView txtUser;
     ImageView editprofile, aboutUs;
    String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private Button btnLogout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public userTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userTab.
     */
    // TODO: Rename and change types and number of parameters
    public static userTab newInstance(String param1, String param2) {
        userTab fragment = new userTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 

        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_user_tab, container, false);

        editprofile = v.findViewById(R.id.editprof);
        aboutUs = v.findViewById(R.id.aboutUs);


        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Users").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String user_name = snapshot.child("User Name").getValue(String.class);
                TextView userName = (TextView) getActivity().findViewById(R.id.txtUser);
                userName.setText(user_name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

                btnLogout = v.findViewById(R.id.LogoutBtn);

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                logout();

            } });

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passUserData();
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutUsData();
            }

            private void aboutUsData() {
                Intent intent = new Intent(getActivity().getApplicationContext(), About_Us.class);
                startActivity(intent);
            }
        });



        return v;
    }

    public void passUserData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Users").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String user_name = snapshot.child("User Name").getValue(String.class);
                TextView userName = (TextView) getActivity().findViewById(R.id.txtUser);
                userName.setText(user_name);

                if (snapshot.exists()) {
                    String namedb = snapshot.child("User Name").getValue(String.class);
                    String emaildb = snapshot.child("Mail Id").getValue(String.class);
                    String passdb = snapshot.child("Password").getValue(String.class);
                    String phonedb = snapshot.child("Mobile Number").getValue(String.class);

                    Intent intent = new Intent(getActivity().getApplicationContext(), EditProfile.class);

                    intent.putExtra("User Name", namedb);
                    intent.putExtra("Mail Id", emaildb);
                    intent.putExtra("Password", passdb);
                    intent.putExtra("Mobile Number", phonedb);

                    startActivity(intent);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


            public void logout() {

        FirebaseAuth.getInstance().signOut();

        startActivity(new Intent(getActivity(), Login.class));
    }

    public void aboutUs(View view) {
        Intent intent = new Intent(getActivity().getApplicationContext(), About_Us.class);
        startActivity(intent);

    }
}