package com.example.majhashetkari.DailyRates;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface D_R_interface {

     @GET("c172a76a3cf8581321e4")
    Call<List<DLModel>> getDLModel();
}
