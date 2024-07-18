package ru.point.sprind.entity.viewholder.product.request

import ru.point.domain.entity.view.search.SearchRequestVo
import ru.point.sprind.databinding.RequestHistoryItemBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class RequestViewHolder(
    private val binding: RequestHistoryItemBinding,
) : ViewHolderV2<SearchRequestVo>(binding.root) {

    override fun bind(view: SearchRequestVo) {
        binding.text.text = view.text
    }
}