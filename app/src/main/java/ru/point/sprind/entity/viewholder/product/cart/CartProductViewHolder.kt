package ru.point.sprind.entity.viewholder.product.cart

import coil.load
import coil.size.Scale
import ru.point.domain.entity.view.CartProductVo
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.CartProductCardBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CartProductViewHolder(
    private val binding: CartProductCardBinding,
) : ViewHolderV2<CartProductVo>(binding.root) {

    override fun bind(view: CartProductVo) {
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