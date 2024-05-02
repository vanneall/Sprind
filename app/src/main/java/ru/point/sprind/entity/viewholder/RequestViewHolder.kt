package ru.point.sprind.entity.viewholder

import ru.point.domain.entity.view.Request
import ru.point.sprind.databinding.RequestHistoryItemBinding

class RequestViewHolder(
    private val binding: RequestHistoryItemBinding,
) : ViewHolderV2<Request>(binding.root) {

    override fun bind(view: Request) {
        binding.text.text = view.text
    }
}