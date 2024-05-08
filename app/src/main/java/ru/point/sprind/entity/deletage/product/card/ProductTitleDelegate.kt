package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.ProductTitleVo
import ru.point.sprind.databinding.ProductCardTitleBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.ProductTitleViewHolder

class ProductTitleDelegate : Delegate<ProductTitleVo> {

    override fun isSupported(view: ViewObject) = view is ProductTitleVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ProductTitleVo> {
        val binding = ProductCardTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductTitleViewHolder(binding)
    }
}