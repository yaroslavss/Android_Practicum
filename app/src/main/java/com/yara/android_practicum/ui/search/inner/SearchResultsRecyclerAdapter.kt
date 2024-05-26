package com.yara.android_practicum.ui.search.inner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.ItemSearchResultBinding
import com.yara.core.domain.model.Event
import com.yara.core.domain.model.Events

class SearchResultsRecyclerAdapter(var results: Events) :
    RecyclerView.Adapter<SearchResultsRecyclerAdapter.SearchResultsViewHolder>() {

    inner class SearchResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemSearchResultBinding.bind(itemView)

        private val title = binding.tvSearchResultsText

        fun bind(event: Event) {
            title.text = event.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        return SearchResultsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {
        holder.bind(results[position])
    }
}