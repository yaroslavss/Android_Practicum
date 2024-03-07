package com.yara.android_practicum.ui.help

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.ItemCategoryBinding
import com.yara.android_practicum.domain.model.Category

class CategoriesRecyclerAdapter :
    RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoryViewHolder>() {

    val categories = mutableListOf<Category>()

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCategoryBinding.bind(itemView)

        private val name = binding.tvCategoryName
        private val icon = binding.ivCategoryIcon

        fun bind(category: Category) {
            name.text = category.name
            icon.setImageResource(category.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<Category>) {
        categories.clear()
        categories.addAll(list)
        notifyDataSetChanged()
    }
}