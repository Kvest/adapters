package com.kvest.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

typealias HolderInit = (ViewDataBinding.() -> Unit)?

class DatabindingAdapter<T>(
        context: Context,
        diffCallback : DiffUtil.ItemCallback<T>,
        private val layoutId: Int,
        private val variableId: Int,
        private var holderInit: HolderInit = null
) : ListAdapter<T, DatabindingAdapter.ViewHolder<T>>(diffCallback) {
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        var binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, layoutId, parent, false)
        holderInit?.let {
            holderInit -> binding.holderInit()
        }

        return ViewHolder(binding, variableId)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) = holder.bind(getItem(position))

    class ViewHolder<in T>(
            private val binding: ViewDataBinding,
            private val variableId: Int
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(variableId, item)
            binding.executePendingBindings()
        }
    }
}