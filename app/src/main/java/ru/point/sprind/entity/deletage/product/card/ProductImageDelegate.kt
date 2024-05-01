package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.ProductImageViewObject
import ru.point.sprind.databinding.ProductCardImageBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.ProductImageViewHolder

class ProductImageDelegate : Delegate<ProductImageViewObject> {

    override fun isSupported(view: ViewObject) = view is ProductImageViewObject

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ProductImageViewObject> {
        val binding = ProductCardImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductImageViewHolder(binding)
    }
}