package ru.point.sprind.entity.deletage.product.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.point.domain.entity.view.CartSummaryVo
import ru.point.domain.entity.view.ListView
import ru.point.sprind.databinding.CartSummaryBinding
import ru.point.sprind.entity.deletage.Delegate
import ru.point.sprind.entity.viewholder.ViewHolderV2
import ru.point.sprind.entity.viewholder.product.cart.CartSummaryViewHolder

class CartSummaryDelegate : Delegate {
    override fun forItem(view: ListView): Boolean {
        return view is CartSummaryVo
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolderV2 {
        val binding = CartSummaryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CartSummaryViewHolder(binding = binding)
    }

    override fun bindViewHolder(view: ListView, viewHolder: ViewHolderV2) {
        (viewHolder as? CartSummaryViewHolder)?.let {
            viewHolder.bind(view)
        }
    }
}