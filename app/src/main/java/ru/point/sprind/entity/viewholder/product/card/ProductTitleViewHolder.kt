package ru.point.sprind.entity.viewholder.product.card

import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.ListView
import ru.point.domain.entity.view.ProductTitleView
import ru.point.sprind.databinding.ProductCardTitleBinding

class ProductTitleViewHolder(
    private val productCardTitleBinding: ProductCardTitleBinding,
) : RecyclerView.ViewHolder(productCardTitleBinding.root) {

    fun bind(view: ListView) {
        (view as? ProductTitleView)?.let { productTitle ->
            with(productCardTitleBinding) {
                title.text = productTitle.title
                price.text = productTitle.price.money.toString()
                rating.text = productTitle.rating
            }
        }
    }
}