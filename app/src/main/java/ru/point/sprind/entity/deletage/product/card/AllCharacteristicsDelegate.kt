package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.ProductCharacteristicsVo
import ru.point.sprind.databinding.ProductCharacteristicStartBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CharacteristicSectionViewHolder

class AllCharacteristicsDelegate(
    private val onExpand: () -> Unit,
    private val onCollapse: () -> Unit
) : Delegate<ProductCharacteristicsVo> {

    override fun isSupported(view: ViewObject) = view is ProductCharacteristicsVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<ProductCharacteristicsVo> {
        val binding = ProductCharacteristicStartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicSectionViewHolder(binding, onExpand, onCollapse);
    }
}