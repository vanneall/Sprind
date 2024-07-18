package ru.point.sprind.entity.viewholder.product.card

import ru.point.domain.entity.view.product.info.ProductCharacteristicsVo
import ru.point.sprind.databinding.ProductCharacteristicStartBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CharacteristicSectionViewHolder(
    val binding: ProductCharacteristicStartBinding,
    private val onExpand: () -> Unit,
    private val onCollapse: () -> Unit
) : ViewHolderV2<ProductCharacteristicsVo>(binding.root) {

    private var isExpanded = false

    override fun bind(view: ProductCharacteristicsVo) {
        binding.root.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                binding.dropdownArrow.rotation = 180f
                onExpand()
            } else {
                binding.dropdownArrow.rotation = 0f
                onCollapse()
            }
        }
    }
}