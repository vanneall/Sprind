package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.CharacteristicTitleView
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.ProductCharacteristicTitleBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CharacteristicTitleViewHolder

class CharacteristicTitleDelegate : Delegate<CharacteristicTitleView> {

    override fun isSupported(view: ListView)=view is CharacteristicTitleView

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<CharacteristicTitleView> {
        val binding = ProductCharacteristicTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicTitleViewHolder(binding);
    }
}