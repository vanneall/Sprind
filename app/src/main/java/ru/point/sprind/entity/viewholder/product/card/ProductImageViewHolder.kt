package ru.point.sprind.entity.viewholder.product.card

import coil.load
import coil.size.Scale
import ru.point.domain.entity.view.ProductImageView
import ru.point.sprind.databinding.ProductCardImageBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductImageViewHolder(
    private val binding: ProductCardImageBinding,
) : ViewHolderV2<ProductImageView>(binding.root) {

    override fun bind(view: ProductImageView) {
        (view as? ProductImageView)?.let {
            binding.imageView.load(
                view.url
            ) {
                scale(Scale.FIT)
            }
        }
    }
}