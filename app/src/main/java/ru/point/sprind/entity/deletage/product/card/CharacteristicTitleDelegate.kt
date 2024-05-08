package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.ViewObject
import ru.point.domain.entity.view.product.info.CharacteristicTitleVo
import ru.point.sprind.databinding.ProductCharacteristicTitleBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CharacteristicTitleViewHolder

class CharacteristicTitleDelegate : Delegate<CharacteristicTitleVo> {

    override fun isSupported(view: ViewObject)=view is CharacteristicTitleVo

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CharacteristicTitleVo> {
        val binding = ProductCharacteristicTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicTitleViewHolder(binding);
    }
}