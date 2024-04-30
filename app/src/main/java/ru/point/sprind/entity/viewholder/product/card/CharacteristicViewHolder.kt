package ru.point.sprind.entity.viewholder.product.card

import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.CharacteristicDescriptionView
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.ProductCharacteristicDescriptionBinding

class CharacteristicViewHolder(
    private val binding: ProductCharacteristicDescriptionBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(view: ListView) {
        (view as? CharacteristicDescriptionView)?.let {
            with(binding) {
                title.text = view.name
                value.text = view.description
            }
        }
    }
}