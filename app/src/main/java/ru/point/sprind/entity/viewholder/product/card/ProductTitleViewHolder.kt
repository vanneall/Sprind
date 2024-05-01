package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.ProductTitleView
import ru.point.sprind.databinding.ProductCardTitleBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductTitleViewHolder(
    private val productCardTitleBinding: ProductCardTitleBinding,
) : ViewHolderV2<ProductTitleView>(productCardTitleBinding.root) {

    override fun bind(view: ProductTitleView) {
        (view as? ProductTitleView)?.let { productTitle ->
            with(productCardTitleBinding) {
                title.text = productTitle.title
                price.text = productTitle.price.money.toString()
                rating.text = productTitle.rating
            }
        }
    }
}