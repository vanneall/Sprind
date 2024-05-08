package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.product.info.CharacteristicTitleVo
import ru.point.sprind.databinding.ProductCharacteristicTitleBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CharacteristicTitleViewHolder(
    private val binding: ProductCharacteristicTitleBinding,
) : ViewHolderV2<CharacteristicTitleVo>(binding.root) {

    override fun bind(view: CharacteristicTitleVo) {
        with(binding) {
            title.text = view.title
        }
    }
}