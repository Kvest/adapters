package com.kvest.adapters

import android.support.v7.util.DiffUtil
import com.kvest.adapters.db.TimeEntity

object TimeEntityDiffCallback : DiffUtil.ItemCallback<TimeEntity>() {
    override fun areItemsTheSame(oldItem: TimeEntity, newItem: TimeEntity) = newItem.id == oldItem.id
    override fun areContentsTheSame(oldItem: TimeEntity, newItem: TimeEntity) = newItem == oldItem
}