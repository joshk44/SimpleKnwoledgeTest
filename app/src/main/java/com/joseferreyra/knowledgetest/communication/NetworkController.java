/*
 * Created by Jose Ferreyra on 7/30/18 2:53 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 7/30/18 2:51 AM
 *
 */

package com.joseferreyra.knowledgetest.communication;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joseferreyra.knowledgetest.communication.dto.ArticlesResponse;
import com.joseferreyra.knowledgetest.communication.interfaces.RestArticles;
import com.joseferreyra.knowledgetest.ui.ListInteraction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkController {


    private static Retrofit instance;
    private static final String BASE_URL = "https://newsapi.org/v2/";

    public static Retrofit getRetrofitInstance() {
        if (instance == null) {
            instance = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }


    public static ArticlesResponse requestArticles(final ListInteraction inter) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = getRetrofitInstance();
        ArticlesResponse data = null;

        RestArticles restClient = retrofit.create(RestArticles.class);
        Call<ArticlesResponse> call = restClient.getData("us", "business", "ff53db1398c24eb5ad6b6d1e5ec8c491");

        call.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                switch (response.code()) {
                    case 200:
                        ArticlesResponse data = response.body();
                        inter.listUpdate(data.getArticles());
                        break;
                    default:
                        Log.e("Response code", response.toString());
                        break;
                }
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
        return data;
    }
}