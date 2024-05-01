package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.ProductDescriptionViewObject
import ru.point.sprind.databinding.ProductCardDescriptionBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.ProductDescriptionViewHolder

class ProductDescriptionDelegate : Delegate<ProductDescriptionViewObject> {

    override fun isSupported(view: ViewObject) = view is ProductDescriptionViewObject

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ProductDescriptionViewObject> {
        val binding = ProductCardDescriptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductDescriptionViewHolder(binding)
    }
}