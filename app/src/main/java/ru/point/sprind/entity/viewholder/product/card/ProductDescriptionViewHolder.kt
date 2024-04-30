package ru.point.sprind.entity.viewholder.product.card

import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.ProductDescriptionView
import ru.point.sprind.databinding.ProductCardDescriptionBinding

class ProductDescriptionViewHolder(
    private val binding: ProductCardDescriptionBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(view: ListView) {
        (view as? ProductDescriptionView)?.let {
            with(binding) {
                description.text = view.description
                shopName.text = view.shopName
                categoryName.text = view.categoryName
            }
        }
    }
}