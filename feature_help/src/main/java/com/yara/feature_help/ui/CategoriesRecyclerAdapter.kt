package com.yara.feature_help.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yara.core.domain.model.Category
import com.yara.feature_help.R
import com.yara.feature_help.databinding.ItemCategoryBinding

class CategoriesRecyclerAdapter :
    RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoryViewHolder>() {

    val categories = mutableListOf<Category>()

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCategoryBinding.bind(itemView)
        private val context = itemView.context

        private val name = binding.tvCategoryName
        private val icon = binding.ivCategoryIcon

        fun bind(category: Category) {
            name.text = category.name

            // icon from json asset file
            if (category.icon.isDigitsOnly()) {
                icon.setImageResource(category.icon.toInt())
            } else {
                // icon from network API
                Glide.with(context)
                    .load(category.icon)
                    .placeholder(R.drawable.icon_animals)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(icon)
            }
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