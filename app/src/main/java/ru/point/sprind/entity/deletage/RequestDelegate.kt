package ru.point.sprind.entity.deletage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.sprind.databinding.RequestHistoryItemBinding
import ru.point.domain.entity.ListView
import ru.point.domain.entity.Request
import ru.point.sprind.entity.viewholder.RequestViewHolder

class RequestDelegate : Delegate {
    override fun forItem(view: ru.point.domain.entity.ListView): Boolean = view is ru.point.domain.entity.Request

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = RequestHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RequestViewHolder(binding = binding)
    }

    override fun bindViewHolder(view: ru.point.domain.entity.ListView, viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as? RequestViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}