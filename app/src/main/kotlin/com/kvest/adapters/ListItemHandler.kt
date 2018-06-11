package com.kvest.adapters

import com.kvest.adapters.db.TimeEntity

interface ListItemHandler {
    fun onSelect(item: TimeEntity)
    fun onDelete(id: Long)
}