package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.AllCharacteristicsVo
import ru.point.sprind.databinding.ProductCharacteristicStartBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CharacteristicSectionViewHolder

class AllCharacteristicsDelegate(
    private val onClick: (Boolean) -> Unit,
) : Delegate<AllCharacteristicsVo> {

    override fun isSupported(view: ViewObject) = view is AllCharacteristicsVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<AllCharacteristicsVo> {
        val binding = ProductCharacteristicStartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicSectionViewHolder(binding, onClick);
    }
}