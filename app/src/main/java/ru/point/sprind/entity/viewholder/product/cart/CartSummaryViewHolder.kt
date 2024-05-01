package ru.point.sprind.entity.viewholder.product.cart

import androidx.recyclerview.widget.RecyclerView
import ru.point.domain.entity.view.CartSummaryVo
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.CartSummaryBinding

class CartSummaryViewHolder(
    private val binding: CartSummaryBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(view: ListView) {
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