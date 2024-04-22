package ru.point.sprind.entity.viewholder.product.card

import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.ListView
import ru.point.domain.entity.ProductDescription
import ru.point.sprind.databinding.ProductCardDescriptionBinding

class ProductDescriptionViewHolder(
    private val binding: ProductCardDescriptionBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(view: ListView) {
        (view as? ProductDescription)?.let {
            binding.description.text = view.description
        }
    }
}