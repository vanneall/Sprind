package ru.point.sprind.entity.viewholder.product.card

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import ru.point.domain.entity.ListView
import ru.point.domain.entity.ProductImage
import ru.point.sprind.databinding.ProductCardImageBinding

class ProductImageViewHolder(
    private val binding: ProductCardImageBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productImage: ListView) {
        (productImage as? ProductImage)?.let {
            binding.imageView.load(
                productImage.url
            ) {
                scale(Scale.FIT)
            }
        }
    }
}