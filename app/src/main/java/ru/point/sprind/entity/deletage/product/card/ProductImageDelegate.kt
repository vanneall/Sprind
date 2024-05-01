package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.ProductImageView
import ru.point.sprind.databinding.ProductCardImageBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.ProductImageViewHolder

class ProductImageDelegate : Delegate<ProductImageView> {

    override fun isSupported(view: ListView) = view is ProductImageView

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ProductImageView> {
        val binding = ProductCardImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductImageViewHolder(binding)
    }
}