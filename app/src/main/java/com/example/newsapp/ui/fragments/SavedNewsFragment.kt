package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.ui.adapters.ArticleAdapter

class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSavedNewsBinding? = null
    private val binding: FragmentSavedNewsBinding get() = _binding!!

    private lateinit var viewModel: NewsViewModel

    private lateinit var newsAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as NewsActivity).viewModel
        setupRecyclerView()
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply { putSerializable("article", it) }
            findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment, bundle)
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = ArticleAdapter()
        binding.rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}