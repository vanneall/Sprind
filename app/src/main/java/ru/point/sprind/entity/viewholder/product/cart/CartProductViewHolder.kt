package ru.point.sprind.entity.viewholder.product.cart

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import ru.point.domain.entity.view.CartProductVo
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.CartProductCardBinding

class CartProductViewHolder(
    private val binding: CartProductCardBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(view: ListView) {
        (view as? CartProductVo)?.let {
            with(binding) {
                image.load(
                    view.url
                ) {
                    scale(Scale.FIT)
                }
                name.text = view.name
                price.text = view.price.money.toString()
            }
        }
    }
}