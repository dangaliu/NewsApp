package com.example.newsapp.ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {


    private var _binding: ActivityNewsBinding? = null
    private val binding: ActivityNewsBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setupWithNavController(
            binding.flFragment.findViewById<FrameLayout>(
                R.id.navHostFragment
            ).findNavController()
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}