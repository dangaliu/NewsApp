package com.example.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.ui.models.Article
import com.example.newsapp.ui.models.NewsResponse
import com.example.newsapp.ui.repository.NewsRepository
import com.example.newsapp.ui.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null

    val savedArticles: LiveData<List<Article>> = newsRepository.getAllArticles()

    init {
        getBreakingNews(country = "us")
    }

    fun getBreakingNews(country: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response =
            newsRepository.getBreakingNews(country = country, pageNumber = breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handledSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { responseResult ->
                breakingNewsPage++
                if (breakingNewsResponse == null) {
                    breakingNewsResponse = responseResult
                } else {
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = responseResult.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(responseResult)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handledSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { responseResult ->
                searchNewsPage++
                if (searchNewsResponse == null) {
                    searchNewsResponse = responseResult
                } else {
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = responseResult.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(responseResult)
            }
        }
        return Resource.Error(response.message())
    }

    fun upsert(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun delete(article: Article) = viewModelScope.launch {
        newsRepository.delete(article)
    }
}