package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.ProductDescriptionView
import ru.point.sprind.databinding.ProductCardDescriptionBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductDescriptionViewHolder(
    private val binding: ProductCardDescriptionBinding,
) : ViewHolderV2(binding.root) {

    override fun bind(view: ListView) {
        (view as? ProductDescriptionView)?.let {
            with(binding) {
                description.text = view.description
                shopName.text = view.shopName
                categoryName.text = view.categoryName
            }
        }
    }
}