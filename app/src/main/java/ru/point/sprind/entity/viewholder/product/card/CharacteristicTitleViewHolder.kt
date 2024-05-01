package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.CharacteristicTitleView
import ru.point.sprind.databinding.ProductCharacteristicTitleBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CharacteristicTitleViewHolder(
    private val binding: ProductCharacteristicTitleBinding,
) : ViewHolderV2<CharacteristicTitleView>(binding.root) {

    override fun bind(view: CharacteristicTitleView) {
        (view as? CharacteristicTitleView)?.let {
            with(binding) {
                title.text = view.title
            }
        }
    }
}