package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.CharacteristicTitleView
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.ProductCharacteristicTitleBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.card.CharacteristicTitleViewHolder

class CharacteristicTitleDelegate : Delegate {
    override fun forItem(view: ListView): Boolean {
        return view is CharacteristicTitleView
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2 {
        val binding = ProductCharacteristicTitleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicTitleViewHolder(binding);
    }

    override fun bindViewHolder(view: ListView, viewHolder: ViewHolderV2) {
        (viewHolder as? CharacteristicTitleViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}