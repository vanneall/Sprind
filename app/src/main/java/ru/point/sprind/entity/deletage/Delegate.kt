package ru.point.sprind.entity.deletage

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.point.domain.entity.view.ListView

interface Delegate {
    fun forItem(view: ListView): Boolean
    fun createViewHolder(parent: ViewGroup): ViewHolder
    fun bindViewHolder(view: ListView, viewHolder: ViewHolder)
}