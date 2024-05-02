package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.ProductTitleViewObject
import ru.point.sprind.databinding.ProductCardTitleBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductTitleViewHolder(
    private val productCardTitleBinding: ProductCardTitleBinding,
) : ViewHolderV2<ProductTitleViewObject>(productCardTitleBinding.root) {

    override fun bind(view: ProductTitleViewObject) {
        with(productCardTitleBinding) {
            title.text = view.title
            price.text = view.price.money.toString()
            rating.text = view.rating
        }
    }
}