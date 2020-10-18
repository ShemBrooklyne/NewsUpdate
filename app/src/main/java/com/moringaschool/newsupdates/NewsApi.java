package com.moringaschool.newsupdates;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface NewsApi {
    @GET("everything")
    Call<NewsUpdatesSearchResponse> getEverything(
            @Query("Article") String Article,
            @Query("Source") String Source,
            @Query("qInTitle") String qInTitle,
            @Query("author") String author
    );
}
