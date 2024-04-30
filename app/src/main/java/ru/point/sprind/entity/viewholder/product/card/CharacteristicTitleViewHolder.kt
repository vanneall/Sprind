package ru.point.sprind.entity.viewholder.product.card

import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.CharacteristicTitleView
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.ProductCharacteristicTitleBinding

class CharacteristicTitleViewHolder(
    private val binding: ProductCharacteristicTitleBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(view: ListView) {
        (view as? CharacteristicTitleView)?.let {
            with(binding) {
                title.text = view.title
            }
        }
    }
}