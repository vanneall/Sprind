package ru.point.sprind.entity.deletage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.sprind.databinding.RequestHistoryItemBinding
import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.Request
import ru.point.sprind.entity.viewholder.RequestViewHolder

class RequestDelegate : Delegate {
    override fun forItem(view: ListView): Boolean = view is Request

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = RequestHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RequestViewHolder(binding = binding)
    }

    override fun bindViewHolder(view: ListView, viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as? RequestViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}