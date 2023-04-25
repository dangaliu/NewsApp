package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.ui.models.Article

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        with(holder.binding) {
            article.urlToImage?.let {
                Glide.with(root.context).load(it).into(ivArticleImage)
            }
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvSource.text = article.source.name
            tvPublishedAt.text = article.publishedAt
            rootLayout.setOnClickListener {
                onItemClickListener?.let { listener ->
                    listener(article)
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        this.onItemClickListener = listener
    }
}