package ru.point.sprind.entity.viewholder

import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.Request
import ru.point.sprind.databinding.RequestHistoryItemBinding

class RequestViewHolder(
    private val binding: RequestHistoryItemBinding
) : ViewHolderV2(binding.root) {

    override fun bind(view: ListView) {
        (view as? Request)?.let {
            binding.text.text = view.text
        }
    }
}