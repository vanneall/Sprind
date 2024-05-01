package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.CharacteristicTitleViewObject
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.databinding.ProductCharacteristicTitleBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CharacteristicTitleViewHolder

class CharacteristicTitleDelegate : Delegate<CharacteristicTitleViewObject> {

    override fun isSupported(view: ViewObject)=view is CharacteristicTitleViewObject

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CharacteristicTitleViewObject> {
        val binding = ProductCharacteristicTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicTitleViewHolder(binding);
    }
}