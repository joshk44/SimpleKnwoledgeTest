package com.joseferreyra.knowledgetest.communication.interfaces;

import com.joseferreyra.knowledgetest.communication.dto.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestArticles {

    @GET("top-headlines")
    Call<ArticlesResponse> getData(@Query("country") String country  , @Query("category") String category, @Query("apiKey") String apiKey);
}
