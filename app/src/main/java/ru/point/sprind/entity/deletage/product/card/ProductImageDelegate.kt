package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.ImageUrlVo
import ru.point.sprind.databinding.ProductCardImageBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.ProductImageViewHolder

class ProductImageDelegate : Delegate<ImageUrlVo> {

    override fun isSupported(view: ViewObject) = view is ImageUrlVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ImageUrlVo> {
        val binding = ProductCardImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductImageViewHolder(binding)
    }
}