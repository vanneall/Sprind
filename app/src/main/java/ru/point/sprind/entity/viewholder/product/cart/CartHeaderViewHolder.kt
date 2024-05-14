package ru.point.sprind.entity.viewholder.product.cart

import ru.point.domain.entity.view.cart.CartHeaderVo
import ru.point.sprind.databinding.CartDeliveryHeaderBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CartHeaderViewHolder(
    binding: CartDeliveryHeaderBinding,
) : ViewHolderV2<CartHeaderVo>(binding.root) {
    override fun bind(view: CartHeaderVo) {}
}