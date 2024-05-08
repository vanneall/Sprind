package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.product.info.ProductDescriptionVo
import ru.point.sprind.databinding.ProductCardDescriptionBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductDescriptionViewHolder(
    private val binding: ProductCardDescriptionBinding,
) : ViewHolderV2<ProductDescriptionVo>(binding.root) {

    override fun bind(view: ProductDescriptionVo) {
        with(binding) {
            description.text = view.description
            shopName.text = view.shopName
            categoryName.text = view.categoryName
        }
    }
}