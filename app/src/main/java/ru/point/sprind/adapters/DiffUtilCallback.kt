package ru.point.sprind.adapters

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback<T>(
    private val itemsComparator: (T, T) -> Boolean = { oldItem, newItem -> oldItem == newItem }
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return itemsComparator.invoke(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return itemsComparator.invoke(oldItem, newItem)
    }

    override fun getChangePayload(oldItem: T & Any, newItem: T & Any): Any {
        return Any()
    }
}