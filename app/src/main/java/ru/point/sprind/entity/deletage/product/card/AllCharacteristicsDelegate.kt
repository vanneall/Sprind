package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.AllCharacteristicsView
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.ProductCharacteristicStartBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CharacteristicSectionViewHolder

class AllCharacteristicsDelegate(
    private val onClick: (Boolean) -> Unit,
) : Delegate {
    override fun forItem(view: ListView): Boolean {
        return view is AllCharacteristicsView
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2 {
        val binding = ProductCharacteristicStartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicSectionViewHolder(binding, onClick);
    }

    override fun bindViewHolder(view: ListView, viewHolder: ViewHolderV2) {
        (viewHolder as? CharacteristicSectionViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}