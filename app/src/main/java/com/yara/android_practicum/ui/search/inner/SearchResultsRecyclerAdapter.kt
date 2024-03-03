package com.yara.android_practicum.ui.search.inner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yara.android_practicum.databinding.ItemSearchResultBinding

class SearchResultsRecyclerAdapter(var results: List<String>) :
    RecyclerView.Adapter<SearchResultsRecyclerAdapter.SearchResultsViewHolder>() {

    private var _binding: ItemSearchResultBinding? = null
    private val binding get() = _binding!!

    inner class SearchResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        _binding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultsViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {
        val item = results[position]
        holder.itemView.apply {
            binding.tvSearchResultsText.text = item
        }
    }
}