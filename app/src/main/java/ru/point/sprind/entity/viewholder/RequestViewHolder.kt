package ru.point.sprind.entity.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.point.sprind.databinding.RequestHistoryItemBinding
import ru.point.domain.entity.ListView
import ru.point.domain.entity.Request

class RequestViewHolder(
    private val binding: RequestHistoryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(view: ru.point.domain.entity.ListView) {
        (view as? ru.point.domain.entity.Request)?.let {
            binding.text.text = view.text
        }
    }
}