package ru.point.sprind.entity.viewholder.product.cart

import ru.point.domain.entity.view.cart.CartEmptyVo
import ru.point.sprind.databinding.CartEmptyWarningBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CartEmptyViewHolder(
    private val binding: CartEmptyWarningBinding,
) : ViewHolderV2<CartEmptyVo>(binding.root) {

    override fun bind(view: CartEmptyVo) {}
}