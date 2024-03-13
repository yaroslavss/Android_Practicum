package com.yara.android_practicum.ui.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yara.android_practicum.R
import com.yara.android_practicum.databinding.ItemEventBinding
import com.yara.android_practicum.domain.model.Event

class EventsRecyclerAdapter : RecyclerView.Adapter<EventsRecyclerAdapter.EventViewHolder>() {

    val events = mutableListOf<Event>()

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemEventBinding.bind(itemView)
        private val context = itemView.context

        private val title = binding.tvEventTitle
        private val image = binding.ivEventIcon
        private val description = binding.tvEventDescriptionText
        private val bottomPane = binding.tvEventBottomPaneText

        @SuppressLint("DiscouragedApi")
        fun bind(event: Event) {
            title.text = event.title
            image.setImageResource(
                context.resources.getIdentifier(
                    event.images.first(),
                    "drawable",
                    context.packageName
                )
            )
            description.text = event.description
            bottomPane.text = event.dateString
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<Event>) {
        events.clear()
        events.addAll(list)
        notifyDataSetChanged()
    }
}