package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.product.info.ProductReviewVo
import ru.point.sprind.databinding.ProductCardReviewBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CardReviewViewHolder(
    private val binding: ProductCardReviewBinding,
    private val onClick: () -> Unit
) : ViewHolderV2<ProductReviewVo>(binding.root) {

    override fun bind(view: ProductReviewVo) {

        binding.root.setOnClickListener { onClick() }
    }
}