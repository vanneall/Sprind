package ru.point.sprind.entity.viewholder.order

import ru.point.domain.entity.view.order.OrderVo
import ru.point.sprind.R
import ru.point.sprind.databinding.OrderCardBinding
import ru.point.sprind.entity.viewholder.ViewHolderV2

class OrderViewHolder(
    private val binding: OrderCardBinding
) : ViewHolderV2<OrderVo>(binding.root) {

    override fun bind(view: OrderVo) {

        var orderId: String
        var deliveryPrice: String
        var productsCost: String
        var summaryCost: String

        with(binding.root.resources) {
            orderId = getString(R.string.order_id, view.id.toString())
            deliveryPrice = getString(R.string.orders_delivery_price, view.deliveryPrice)
            productsCost = getString(R.string.order_products_price, view.productsPrice)
            summaryCost = getString(R.string.order_summary_price, view.summaryPrice)
        }

        with(binding) {
            this.orderId.text = orderId
            this.deliveryCost.text = deliveryPrice
            this.productsCost.text = productsCost
            this.summaryCost.text = summaryCost
        }
    }
}