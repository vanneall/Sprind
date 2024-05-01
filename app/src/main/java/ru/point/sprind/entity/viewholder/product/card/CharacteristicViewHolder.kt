package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.CharacteristicDescriptionViewObject
import ru.point.sprind.databinding.ProductCharacteristicDescriptionBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CharacteristicViewHolder(
    private val binding: ProductCharacteristicDescriptionBinding,
) : ViewHolderV2<CharacteristicDescriptionViewObject>(binding.root) {

    override fun bind(view: CharacteristicDescriptionViewObject) {
        (view as? CharacteristicDescriptionViewObject)?.let {
            with(binding) {
                title.text = view.name
                value.text = view.description
            }
        }
    }
}