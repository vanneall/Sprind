package ru.point.sprind.entity.viewholder.product.cart

import ru.point.domain.entity.view.cart.CartPromocodeVo
import ru.point.sprind.databinding.CartPromocodeBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CartPromocodeViewHolder(
    private val binding: CartPromocodeBinding,
) : ViewHolderV2<CartPromocodeVo>(binding.root) {

    override fun bind(view: CartPromocodeVo) {}
}