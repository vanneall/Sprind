package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ProductReviewVo
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.databinding.ProductCardReviewBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CardReviewViewHolder

class ProductCardReviewDelegate(private val onClick: () -> Unit) : Delegate<ProductReviewVo> {

    override fun isSupported(view: ViewObject) = view is ProductReviewVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ProductReviewVo> {
        val binding = ProductCardReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CardReviewViewHolder(binding, onClick)
    }
}