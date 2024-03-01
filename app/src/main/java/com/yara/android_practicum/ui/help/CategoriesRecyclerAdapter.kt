package com.yara.android_practicum.ui.help

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yara.android_practicum.databinding.ItemCategoryBinding
import com.yara.android_practicum.domain.model.Category

class CategoriesRecyclerAdapter(var categories: List<Category>) :
    RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoryViewHolder>() {

    private var _binding: ItemCategoryBinding? = null
    private val binding get() = _binding!!

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        _binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories[position]
        holder.itemView.apply {
            binding.tvCategoryName.text = item.name
            binding.ivCategoryIcon.setImageResource(item.icon)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}