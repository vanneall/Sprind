package ru.point.sprind.entity.viewholder.product.request

import ru.point.domain.entity.view.request.RequestVo
import ru.point.sprind.databinding.RequestHistoryItemBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class RequestViewHolder(
    private val binding: RequestHistoryItemBinding,
) : ViewHolderV2<RequestVo>(binding.root) {

    override fun bind(view: RequestVo) {
        binding.text.text = view.text
    }
}