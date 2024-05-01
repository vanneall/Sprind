package ru.point.sprind.entity.deletage

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.dto.FeedProductDto
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.databinding.VerticalCardItemBinding
import ru.point.sprind.entity.viewholder.ProductViewHolder
import ru.point.sprind.entity.viewholder.ViewHolderV2

class ProductDelegate(
    private val onClickCard: (Long) -> Unit,
) : Delegate<FeedProductDto> {

    override fun isSupported(view: ViewObject) = view is FeedProductDto

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<FeedProductDto> {
        val binding = VerticalCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding, onClickCard)
    }
}