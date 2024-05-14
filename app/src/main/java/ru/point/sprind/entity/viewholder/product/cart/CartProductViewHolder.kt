package ru.point.sprind.entity.viewholder.product.cart

import coil.load
import coil.size.Scale
import ru.point.domain.entity.view.cart.CartProductVo
import ru.point.sprind.databinding.CartProductCardBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CartProductViewHolder(
    private val binding: CartProductCardBinding,
    private val onClick: (Long) -> Unit
) : ViewHolderV2<CartProductVo>(binding.root) {

    override fun bind(view: CartProductVo) {
        with(binding) {
            image.load(
                view.url
            ) {
                scale(Scale.FIT)
            }
            name.text = view.name
            price.text = view.price

            root.setOnClickListener {
                onClick(view.id)
            }
        }
    }
}