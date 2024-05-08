package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.product.info.ProductTitleVo
import ru.point.sprind.databinding.ProductCardTitleBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductTitleViewHolder(
    private val productCardTitleBinding: ProductCardTitleBinding,
) : ViewHolderV2<ProductTitleVo>(productCardTitleBinding.root) {

    override fun bind(view: ProductTitleVo) {
        with(productCardTitleBinding) {
            title.text = view.title
            price.text = view.price.money.toString()
            rating.text = view.rating
        }
    }
}