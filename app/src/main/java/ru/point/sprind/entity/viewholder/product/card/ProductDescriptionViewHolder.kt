package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.ProductDescriptionViewObject
import ru.point.sprind.databinding.ProductCardDescriptionBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductDescriptionViewHolder(
    private val binding: ProductCardDescriptionBinding,
) : ViewHolderV2<ProductDescriptionViewObject>(binding.root) {

    override fun bind(view: ProductDescriptionViewObject) {
        with(binding) {
            description.text = view.description
            shopName.text = view.shopName
            categoryName.text = view.categoryName
        }
    }
}