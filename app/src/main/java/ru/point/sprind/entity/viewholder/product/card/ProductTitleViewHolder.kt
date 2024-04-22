package ru.point.sprind.entity.viewholder.product.card

import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.ListView
import ru.point.domain.entity.ProductTitle
import ru.point.sprind.databinding.ProductCardTitleBinding

class ProductTitleViewHolder(
    private val productCardTitleBinding: ProductCardTitleBinding,
) : RecyclerView.ViewHolder(productCardTitleBinding.root) {

    fun bind(view: ListView) {
        (view as? ProductTitle)?.let { productTitle ->
            with(productCardTitleBinding) {
                title.text = productTitle.title
                price.text = productTitle.price.money.toString()
                rating.text = productTitle.rating
            }
        }
    }
}