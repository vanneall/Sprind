package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup

import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.CharacteristicDescriptionVo
import ru.point.sprind.databinding.ProductCharacteristicDescriptionBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CharacteristicViewHolder

class CharacteristicDelegate: Delegate<CharacteristicDescriptionVo> {

    override fun isSupported(view: ViewObject) = view is CharacteristicDescriptionVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CharacteristicDescriptionVo> {
        val binding = ProductCharacteristicDescriptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicViewHolder(binding);
    }
}