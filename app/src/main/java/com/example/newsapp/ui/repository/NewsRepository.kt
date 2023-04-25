package com.example.newsapp.ui.repository

import com.example.newsapp.ui.api.RetrofitInstance
import com.example.newsapp.ui.db.NewsDatabase

class NewsRepository(private val db: NewsDatabase) {

    suspend fun getBreakingNews(country: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(country = country, pageNum = pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery = searchQuery, pageNum = pageNumber)
}