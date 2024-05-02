package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.CharacteristicTitleViewObject
import ru.point.sprind.databinding.ProductCharacteristicTitleBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CharacteristicTitleViewHolder(
    private val binding: ProductCharacteristicTitleBinding,
) : ViewHolderV2<CharacteristicTitleViewObject>(binding.root) {

    override fun bind(view: CharacteristicTitleViewObject) {
        with(binding) {
            title.text = view.title
        }
    }
}