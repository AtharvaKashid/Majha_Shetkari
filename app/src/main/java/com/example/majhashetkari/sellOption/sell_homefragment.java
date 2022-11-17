package com.example.majhashetkari.sellOption;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.majhashetkari.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class sell_homefragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;

    public sell_homefragment() {

        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static sell_homefragment newInstance(String param1, String param2) {
        sell_homefragment fragment = new sell_homefragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle(" Home");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sell_homefragment, container, false);
        recyclerView = view.findViewById(R.id.productlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




   /*     SellProduct pr1 = new SellProduct(R.drawable.pr1,"Fertilizer",5400);
        sellProducts.add(pr1);
        SellProduct pr2 = new SellProduct(R.drawable.pr2,"Fertilizer",5400);
        sellProducts.add(pr2);
        SellProduct pr3 = new SellProduct(R.drawable.pr3,"Fertilizer",5400);
        sellProducts.add(pr3);
        SellProduct pr4 = new SellProduct(R.drawable.pr4,"Fertilizer",5400);
        sellProducts.add(pr4);*/



        return view;
    }


}