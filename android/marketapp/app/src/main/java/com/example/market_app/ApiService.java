package com.example.market_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("article/list")
    Call<List<Article>> getArticleList();

    @GET("article/info/{uuid}")
    Call<Article> getArticle(@Path("uuid") String uuid);

    @POST("article/create")
    Call<Article> createArticle(@Body Article article);

    @DELETE("article/delete/{uuid}")
    Call<Void> deleteArticle(@Path("uuid") String uuid);

    @PATCH("article/update/{uuid}")
    Call<Article> updateArticle(@Path("uuid") String uuid, @Body Article article);

    @POST("user/login")
    Call<User> login(@Body User user);

    @POST("user/register")
    Call<User> register(@Body User user);
}
