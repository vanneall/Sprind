package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.ProductDescriptionView
import ru.point.sprind.databinding.ProductCardDescriptionBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.ProductDescriptionViewHolder

class ProductDescriptionDelegate : Delegate<ProductDescriptionView> {

    override fun isSupported(view: ListView) = view is ProductDescriptionView

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ProductDescriptionView> {
        val binding = ProductCardDescriptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductDescriptionViewHolder(binding)
    }
}