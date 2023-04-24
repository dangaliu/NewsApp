package com.example.newsapp.ui.api

import com.example.newsapp.NewsResponse
import com.example.newsapp.ui.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") country: String = "ru",
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("page") pageNum: Int = 1
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("page") pageNum: Int = 1
    ): Response<NewsResponse>
}