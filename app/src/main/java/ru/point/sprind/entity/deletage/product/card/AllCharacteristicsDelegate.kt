package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.AllCharacteristicsViewObject
import ru.point.domain.entity.view.ViewObject
import ru.point.sprind.databinding.ProductCharacteristicStartBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CharacteristicSectionViewHolder

class AllCharacteristicsDelegate(
    private val onClick: (Boolean) -> Unit,
) : Delegate<AllCharacteristicsViewObject> {

    override fun isSupported(view: ViewObject) = view is AllCharacteristicsViewObject

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2<AllCharacteristicsViewObject> {
        val binding = ProductCharacteristicStartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicSectionViewHolder(binding, onClick);
    }
}