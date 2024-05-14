package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.product.info.AllCharacteristicsVo
import ru.point.sprind.R
import ru.point.sprind.databinding.ProductCharacteristicStartBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CharacteristicSectionViewHolder(
    val binding: ProductCharacteristicStartBinding,
    private val onClick: (Boolean) -> Unit,
) : ViewHolderV2<AllCharacteristicsVo>(binding.root) {

    private var isExpanded = false

    override fun bind(view: AllCharacteristicsVo) {
        binding.root.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                binding.root.setBackgroundResource(R.drawable.card_background_full)
                binding.dropdownArrow.rotation = 180f
            } else {
                binding.root.setBackgroundResource(R.drawable.card_background_full)
                binding.dropdownArrow.rotation = 0f
            }
            onClick(isExpanded)
        }
    }
}