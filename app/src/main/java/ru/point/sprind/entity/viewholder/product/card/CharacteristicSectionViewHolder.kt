package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.ListView
import ru.point.sprind.R
import ru.point.sprind.databinding.ProductCharacteristicStartBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CharacteristicSectionViewHolder(
    val binding: ProductCharacteristicStartBinding,
    private val onClick: (Boolean) -> Unit,
) : ViewHolderV2(binding.root) {

    private var isExpanded = false

    override fun bind(view: ListView) {
        binding.root.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                binding.root.setBackgroundResource(R.drawable.top_card_shape)
                binding.dropdownArrow.rotation = 180f
            } else {
                binding.root.setBackgroundResource(R.drawable.card_background)
                binding.dropdownArrow.rotation = 0f
            }
            onClick(isExpanded)
        }
    }
}