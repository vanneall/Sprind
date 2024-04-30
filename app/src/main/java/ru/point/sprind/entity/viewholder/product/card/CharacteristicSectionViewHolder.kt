package ru.point.sprind.entity.viewholder.product.card

import androidx.recyclerview.widget.RecyclerView
import ru.point.sprind.R
import ru.point.sprind.databinding.ProductCharacteristicStartBinding

class CharacteristicSectionViewHolder(
    val binding: ProductCharacteristicStartBinding,
    private val onClick: (Boolean) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var isExpanded = false

    fun bind() {
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