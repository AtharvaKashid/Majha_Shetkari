package com.example.majhashetkari.DailyRates;



import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.majhashetkari.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dailyratesTab extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Dadapter dadapter;
    List<DLModel> modelList = new ArrayList<>();


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public dailyratesTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment dailyratesTab.
     */
    // TODO: Rename and change types and number of parameters
    public static dailyratesTab newInstance(String param1, String param2) {
        dailyratesTab fragment = new dailyratesTab();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dailyrates_tab, container, false);

        recyclerView = v.findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        dadapter = new Dadapter(modelList);
        recyclerView.setAdapter(dadapter);

        fetchdata();

        swipeRefreshLayout = v.findViewById(R.id.swipetorefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //modelList.clear();
                fetchdata();
                dadapter.notifyDataSetChanged();
               modelList.clear();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(com.mancj.materialsearchbar.R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        return v;
    }


    private void fetchdata() {
        D_R_RetrofitClient.getRetrofitClient().getDLModel().enqueue(new Callback<List<DLModel>>() {
            @Override
            public void onResponse(Call<List<DLModel>> call, Response<List<DLModel>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    modelList.addAll(response.body());
                    dadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<DLModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Error!!"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}