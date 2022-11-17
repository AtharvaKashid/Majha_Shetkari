package com.example.majhashetkari;

import android.content.Context;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.majhashetkari.buyOption.BuyCrops;
import com.example.majhashetkari.sellOption.SellCrops;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeTab extends Fragment {

    private ImageView buycrops;
    private ImageView sellcrops;
    Spinner spinner;
    private RecyclerView recyclerView;
    private DatabaseReference datarefer;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam2;

    public homeTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeTab.
     */
    // TODO: Rename and change types and number of parameters
    public static homeTab newInstance(String param1, String param2) {
        homeTab fragment = new homeTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home_tab, container, false);

        spinner = (Spinner) rootView.findViewById(R.id.spinner);

        List<String> list = new ArrayList<>();
        list.add("Choose Language..");
        list.add("English ->");
        list.add("Marathi ->");
        list.add("Hindi ->");

        recyclerView = rootView.findViewById(R.id.recyclerview);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("news");
        Query personsquery = ref.orderByKey();

        ImageView button = (ImageView) rootView.findViewById(R.id.buycrops);
        ImageView button1 = (ImageView) rootView.findViewById(R.id.sellcrops);




        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), BuyCrops.class);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SellCrops.class);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            }
        });

        return rootView;
    }

            public static class Holder extends RecyclerView.ViewHolder {
                View v;
                ImageView img1;
                TextView header, link;

                public Holder(View itemView) {
                    super(itemView);
                    img1 = itemView.findViewById(R.id.image);
                    header = itemView.findViewById(R.id.header);
                    link = itemView.findViewById(R.id.link);
                }

                public void setHeader(String Header) {
                    TextView h = v.findViewById(R.id.header);
                    h.setText(Header);
                }

                public void setLink(String link) {
                    TextView Link = v.findViewById(R.id.link);
                    Link.setText(link);
                }

                public void setImage(Context ctx, String image) {
                    img1 = v.findViewById(R.id.image);
                    Picasso.with(ctx).load(image).into(img1);
                }
            }

        }