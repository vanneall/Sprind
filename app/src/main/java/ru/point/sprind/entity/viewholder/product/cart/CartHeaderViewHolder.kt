package ru.point.sprind.entity.viewholder.product.cart

import ru.point.domain.entity.view.cart.CartHeaderVo
import ru.point.sprind.R
import ru.point.sprind.databinding.CartDeliveryHeaderBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CartHeaderViewHolder(
    private val binding: CartDeliveryHeaderBinding,
) : ViewHolderV2<CartHeaderVo>(binding.root) {
    override fun bind(view: CartHeaderVo) {
        if (view.address.address != null) {
            binding.address.text = view.address.address
            binding.address.setTextColor(
                binding.root.resources.getColor(
                    R.color.brown_orange
                )
            )
        }
    }
}