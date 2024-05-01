package ru.point.sprind.entity.viewholder.product.card

import coil.load
import coil.size.Scale
import ru.point.domain.entity.view.ProductImageViewObject
import ru.point.sprind.databinding.ProductCardImageBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductImageViewHolder(
    private val binding: ProductCardImageBinding,
) : ViewHolderV2<ProductImageViewObject>(binding.root) {

    override fun bind(view: ProductImageViewObject) {
        (view as? ProductImageViewObject)?.let {
            binding.imageView.load(
                view.url
            ) {
                scale(Scale.FIT)
            }
        }
    }
}