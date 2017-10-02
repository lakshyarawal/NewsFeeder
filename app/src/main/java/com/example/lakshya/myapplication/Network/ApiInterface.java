package com.example.lakshya.myapplication.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LAKSHYA on 8/27/2017.
 */

public interface ApiInterface {
    @GET("v1/articles")
    Call<ArticleResponse> getArticleResponse(@Query("source") String source,
                                             @Query("sortBy") String sortBy,
                                             @Query("apiKey") String apiKey);
    @GET("v1/sources")
    Call<SourceResponse> getSourceResponse(@Query("language") String language);
}
