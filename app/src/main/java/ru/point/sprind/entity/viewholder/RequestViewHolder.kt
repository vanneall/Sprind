package ru.point.sprind.entity.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.Request
import ru.point.sprind.databinding.RequestHistoryItemBinding

class RequestViewHolder(
    private val binding: RequestHistoryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(view: ListView) {
        (view as? Request)?.let {
            binding.text.text = view.text
        }
    }
}