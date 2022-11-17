package com.example.majhashetkari.DailyRates;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class D_R_RetrofitClient {

    private static final String BASE_URL = "https://api.npoint.io/";
    private static Retrofit retrofit = null;

    public static D_R_interface getRetrofitClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(D_R_interface.class);
    }

}
