package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.CharacteristicDescriptionView
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.ProductCharacteristicDescriptionBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CharacteristicViewHolder(
    private val binding: ProductCharacteristicDescriptionBinding,
) : ViewHolderV2(binding.root) {

    override fun bind(view: ListView) {
        (view as? CharacteristicDescriptionView)?.let {
            with(binding) {
                title.text = view.name
                value.text = view.description
            }
        }
    }
}