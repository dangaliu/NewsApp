package com.example.newsapp.ui.repository

import com.example.newsapp.ui.api.RetrofitInstance
import com.example.newsapp.ui.db.NewsDatabase
import com.example.newsapp.ui.models.Article

class NewsRepository(private val db: NewsDatabase) {

    suspend fun getBreakingNews(country: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(country = country, pageNum = pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery = searchQuery, pageNum = pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    suspend fun delete(article: Article) = db.getArticleDao().deleteArticle(article)

    fun getAllArticles() = db.getArticleDao().getAllArticles()
}