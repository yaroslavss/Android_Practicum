package com.yara.android_practicum.ui.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.ItemPhotoDialogMenuBinding
import com.yara.android_practicum.domain.model.PhotoDialogMenuItem

class MenuItemsRecyclerAdapter(private val onItemClick: (menuItem: PhotoDialogMenuItem) -> Unit) :
    RecyclerView.Adapter<MenuItemsRecyclerAdapter.MenuItemViewHolder>() {

    val menuItems = mutableListOf<PhotoDialogMenuItem>()

    inner class MenuItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemPhotoDialogMenuBinding.bind(itemView)

        private val title = binding.tvMenuItemLabel
        private val icon = binding.ivMenuItemIcon

        fun bind(menuItem: PhotoDialogMenuItem) {
            title.text = menuItem.title
            icon.setImageResource(menuItem.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        return MenuItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo_dialog_menu, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val item = menuItems[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<PhotoDialogMenuItem>) {
        menuItems.clear()
        menuItems.addAll(list)
        notifyDataSetChanged()
    }
}