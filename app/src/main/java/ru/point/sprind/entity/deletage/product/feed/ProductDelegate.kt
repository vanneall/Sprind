package ru.point.sprind.entity.deletage.product.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.card.ProductFeedVo
import ru.point.sprind.databinding.VerticalCardItemBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.feed.ProductViewHolder

class ProductDelegate(
    private val onClickCard: (Long) -> Unit,
    private val onBuyClick: (Long) -> Unit,
    private val onFavoriteCheckedChange: (Long, Boolean, (Boolean) -> Unit) -> Unit,
) : Delegate<ProductFeedVo> {

    override fun isSupported(view: ViewObject) = view is ProductFeedVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ProductFeedVo> {
        val binding = VerticalCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(
            binding = binding,
            onClickCard = onClickCard,
            onBuyClick = onBuyClick,
            onFavoriteCheckedChange = onFavoriteCheckedChange
        )
    }
}