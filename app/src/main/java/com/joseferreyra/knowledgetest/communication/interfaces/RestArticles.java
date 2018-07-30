/*
 * Created by Jose Ferreyra on 7/30/18 2:53 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 7/30/18 2:51 AM
 *
 */

package com.joseferreyra.knowledgetest.communication.interfaces;

import com.joseferreyra.knowledgetest.communication.dto.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestArticles {

    /**
     * Return a paged news with 20 articles from the server.
     *
     * @param country
     * @param category
     * @param apiKey provided by the backend api.
     * @return
     */
    @GET("top-headlines")
    Call<ArticlesResponse> getData(@Query("country") String country  , @Query("category") String category, @Query("apiKey") String apiKey);
}
