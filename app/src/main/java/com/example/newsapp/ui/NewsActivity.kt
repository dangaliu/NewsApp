package com.example.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.ui.db.NewsDatabase
import com.example.newsapp.ui.repository.NewsRepository

class NewsActivity : AppCompatActivity() {


    private var _binding: ActivityNewsBinding? = null
    private val binding: ActivityNewsBinding get() = _binding!!

    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = NewsDatabase(this)
        val newsRepository = NewsRepository(db)
        val viewModelFactory = NewsViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(
            navHostFragment.navController
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}