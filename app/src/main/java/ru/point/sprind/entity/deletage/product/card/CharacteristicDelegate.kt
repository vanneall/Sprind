package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.CharacteristicDescriptionView
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.ProductCharacteristicDescriptionBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CharacteristicViewHolder

class CharacteristicDelegate: Delegate<CharacteristicDescriptionView> {

    override fun isSupported(view: ListView) = view is CharacteristicDescriptionView

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CharacteristicDescriptionView> {
        val binding = ProductCharacteristicDescriptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicViewHolder(binding);
    }
}