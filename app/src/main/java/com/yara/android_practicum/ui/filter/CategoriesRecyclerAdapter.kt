package com.yara.android_practicum.ui.filter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.materialswitch.MaterialSwitch
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.ItemFilterCategoryBinding
import com.yara.android_practicum.domain.model.Category

class CategoriesRecyclerAdapter(
    private val filters: Set<Int>,
    private val onItemClick: (category: Category, switch: MaterialSwitch) -> Unit
) :
    RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoryViewHolder>() {

    val categories = mutableListOf<Category>()

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemFilterCategoryBinding.bind(itemView)

        private val name = binding.tvFilterCategoryName
        val switch = binding.msSwitchCategory

        fun bind(category: Category) {
            name.text = category.name
            switch.isChecked = filters.contains(category.id)
            switch.isClickable = false  // handle click by recycler's onItemClick()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filter_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClick(item, holder.switch) }
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