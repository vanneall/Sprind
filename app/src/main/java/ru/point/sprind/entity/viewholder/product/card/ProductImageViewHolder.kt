package ru.point.sprind.entity.viewholder.product.card

import coil.load
import coil.size.Scale
import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.ProductImageView
import ru.point.sprind.databinding.ProductCardImageBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductImageViewHolder(
    private val binding: ProductCardImageBinding,
) : ViewHolderV2(binding.root) {

    override fun bind(view: ListView) {
        (view as? ProductImageView)?.let {
            binding.imageView.load(
                view.url
            ) {
                scale(Scale.FIT)
            }
        }
    }
}