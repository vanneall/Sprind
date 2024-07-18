package ru.point.sprind.entity.viewholder.product.request

import ru.point.domain.entity.view.search.EmptyRequestResultVo
import ru.point.sprind.databinding.RequestResultEmptyBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class EmptyRequestResultViewHolder(
    binding: RequestResultEmptyBinding,
) : ViewHolderV2<EmptyRequestResultVo>(binding.root) {

    override fun bind(view: EmptyRequestResultVo) {}
}