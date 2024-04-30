package ru.point.sprind.entity.deletage.product.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.CharacteristicDescriptionView
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.ProductCharacteristicDescriptionBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.product.card.CharacteristicViewHolder

class CharacteristicDelegate: Delegate {
    override fun forItem(view: ListView): Boolean {
        return view is CharacteristicDescriptionView
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ProductCharacteristicDescriptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacteristicViewHolder(binding);
    }

    override fun bindViewHolder(view: ListView, viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as? CharacteristicViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}