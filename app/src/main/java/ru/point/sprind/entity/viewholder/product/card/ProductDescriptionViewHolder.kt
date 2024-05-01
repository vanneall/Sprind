package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.ProductDescriptionView
import ru.point.sprind.databinding.ProductCardDescriptionBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductDescriptionViewHolder(
    private val binding: ProductCardDescriptionBinding,
) : ViewHolderV2<ProductDescriptionView>(binding.root) {

    override fun bind(view: ProductDescriptionView) {
        (view as? ProductDescriptionView)?.let {
            with(binding) {
                description.text = view.description
                shopName.text = view.shopName
                categoryName.text = view.categoryName
            }
        }
    }
}