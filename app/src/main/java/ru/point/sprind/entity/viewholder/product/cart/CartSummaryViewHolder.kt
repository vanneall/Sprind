package ru.point.sprind.entity.viewholder.product.cart

import ru.point.domain.entity.view.CartSummaryVo
import ru.point.sprind.databinding.CartSummaryBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class CartSummaryViewHolder(
    private val binding: CartSummaryBinding,
) : ViewHolderV2<CartSummaryVo>(binding.root) {

    override fun bind(view: CartSummaryVo) {
        (view as? CartSummaryVo)?.let { vo ->
            with(binding) {
                deliveryPrice.text = vo.delivery
                productsSumPrice.text = vo.products
                discountPrice.text = vo.discount
                promocodePrice.text = vo.promocode
                summaryPrice.text = vo.summary
            }
        }
    }
}