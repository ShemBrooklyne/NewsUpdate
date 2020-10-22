package com.moringaschool.newsupdates.network;

import com.moringaschool.newsupdates.models.NewsUpdatesSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<NewsUpdatesSearchResponse> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );
}

//https://newsapi.org/v2/top-headlines
