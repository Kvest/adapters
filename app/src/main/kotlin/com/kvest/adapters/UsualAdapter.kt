package com.kvest.adapters

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kvest.adapters.db.TimeEntity
import kotlinx.android.synthetic.main.simple_list_item.view.*

class UsualAdapter(
        context: Context,
        private val handler: ListItemHandler
) : ListAdapter<TimeEntity, UsualAdapter.TimeEntityViewHolder>(TimeEntityDiffCallback) {
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeEntityViewHolder {
        val view = layoutInflater.inflate(R.layout.simple_list_item, parent, false)
        return TimeEntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeEntityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TimeEntityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val text = view.text
        private val delete = view.delete

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    handler.onSelect(getItem(position))
                }
            }

            delete.setOnClickListener {
                val position = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    handler.onDelete(getItem(position).id)
                }
            }
        }

        fun bind(entity: TimeEntity) {
            text.text = entity.value
        }
    }
}