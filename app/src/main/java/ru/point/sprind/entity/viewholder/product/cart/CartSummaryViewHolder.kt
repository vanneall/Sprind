package ru.point.sprind.entity.viewholder.product.cart

import ru.point.domain.entity.view.cart.CartSummaryVo
import ru.point.sprind.databinding.CartSummaryBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CartSummaryViewHolder(
    private val binding: CartSummaryBinding,
) : ViewHolderV2<CartSummaryVo>(binding.root) {

    override fun bind(view: CartSummaryVo) {
        with(binding) {
            deliveryPrice.text = view.delivery
            productsSumPrice.text = view.products
            discountPrice.text = view.discount
            promocodePrice.text = view.promocode
            summaryPrice.text = view.summary
        }
    }
}